package pradeep.exp.tchannel;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.uber.tchannel.api.TChannel;
import org.apache.commons.cli.*;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    private static Options options = new Options();

    static {
        options.addOption("p", "port", true, "Server Port to connect to");
        options.addOption("h", "help", false, "Usage");
    }

    public static void main(String[] args ) throws Exception {
        HelpFormatter formatter = new HelpFormatter();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if ( cmd.hasOption("h") ) {
            formatter.printHelp("Server", options, true);
            return;
        }

        int port = Integer.parseInt(cmd.getOptionValue("p", "9000"));

        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10, TimeUnit.SECONDS);

        new Server(port).run(registry);

        System.out.println("stopping....");
    }

    private void run(MetricRegistry registry) throws InterruptedException {
        TChannel tchannel = new TChannel.Builder("server")
                .setServerHost(InetAddress.getLoopbackAddress())
                .setServerPort(port)
                .build();

        tchannel.makeSubChannel("tchannel-server")
                .register("foo", new FooRequestHandler(registry));

        tchannel.listen().channel().closeFuture().sync();

    }
}
