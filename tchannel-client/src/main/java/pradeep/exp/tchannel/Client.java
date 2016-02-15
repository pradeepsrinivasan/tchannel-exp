package pradeep.exp.tchannel;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.uber.tchannel.api.SubChannel;
import com.uber.tchannel.api.TChannel;
import com.uber.tchannel.api.TFuture;
import com.uber.tchannel.api.handlers.TFutureCallback;
import com.uber.tchannel.messages.RawRequest;
import com.uber.tchannel.messages.RawResponse;
import org.apache.commons.cli.*;

import java.net.InetAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by pradeep on 31/01/16.
 */
public class Client {


    private final int port;
    private final int requests;
    private final String host;

    public Client(String host, int port, int requests) {
        this.host = host;
        this.port = port;
        this.requests = requests;
    }

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("h", "host", true, "Server Host to connect to");
        options.addOption("p", "port", true, "Server Port to connect to");
        options.addOption("n", "requests", true, "Number of requests to make");
        options.addOption("?", "help", false, "Usage");
        HelpFormatter formatter = new HelpFormatter();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("?")) {
            formatter.printHelp("Client", options, true);
            return;
        }

        int port = Integer.parseInt(cmd.getOptionValue("p", "9000"));
        String host = cmd.getOptionValue("h", "localhost");
        int requests = Integer.parseInt(cmd.getOptionValue("n", "20000"));
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10, TimeUnit.SECONDS);

        new Client(host, port, requests).run(registry);
        System.out.println("Stopping Client...");
    }

    public void run(MetricRegistry registry) throws Exception {
        TChannel tchannel = new TChannel.Builder("server")
                .setServerHost(InetAddress.getByName(host))
                .setServerPort(port)
                .build();
        SubChannel subChannel = tchannel.makeSubChannel("tchannel-server");
        final CountDownLatch done = new CountDownLatch(requests);
    //        final Timer responses = metrics.timer(name(RequestHandler.class, "responses"));

        final Timer timer = registry.timer("foo-client");

        for (int i = 0; i < requests; i++) {
            RawRequest request = new RawRequest.Builder("tchannel-server", "foo")
                    .setBody(getDataBody())
                    .setHeader("header")
                    .setTimeout(1000)
                    .build();

            final Timer.Context context = timer.time();
            TFuture<RawResponse> f = subChannel.send(request);

            f.addCallback(new TFutureCallback<RawResponse>() {
                @Override
                public void onResponse(RawResponse rawResponse) {
                    context.stop();
                    context.close();
                    done.countDown();
                }
            });
        }

        done.await();

        tchannel.shutdown(false);
    }

    private static final String DATA_BODY = "Hello";

    private String getDataBody() {
        return DATA_BODY;
    }
}
