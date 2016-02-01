package pradeep.exp.tchannel;

import com.uber.tchannel.api.SubChannel;
import com.uber.tchannel.api.TChannel;
import com.uber.tchannel.api.TFuture;
import com.uber.tchannel.api.handlers.TFutureCallback;
import com.uber.tchannel.messages.RawRequest;
import com.uber.tchannel.messages.RawResponse;
import org.apache.commons.cli.*;

import java.net.InetAddress;
import java.util.concurrent.CountDownLatch;

/**
 * Created by pradeep on 31/01/16.
 */
public class Client {

    private final int port;
    private final int requests;

    public Client(int port, int requests) {
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
        int requests = Integer.parseInt(cmd.getOptionValue("n", "2"));

        System.out.println(String.format("Connecting from client to server on port: %d", port));
        new Client(port, requests).run();
        System.out.println("Stopping Client...");
    }

    public void run() throws Exception {
        TChannel tchannel = new TChannel.Builder("server").build();
        SubChannel subChannel = tchannel.makeSubChannel("tchannel-server");
        final CountDownLatch done = new CountDownLatch(requests);

        for (int i = 0; i < requests; i++) {
            RawRequest request = new RawRequest.Builder("tchannel-server", "foo")
                    .setBody("hello")
                    .setHeader("header")
                    .setTimeout(1000)
                    .build();

            TFuture<RawResponse> f = subChannel.send(
                    request,
                    InetAddress.getByName("localhost"),
                    port
            );

            f.addCallback(new TFutureCallback<RawResponse>() {
                @Override
                public void onResponse(RawResponse rawResponse) {
                    done.countDown();
                    System.out.println(rawResponse.getBody());
                }

            });
        }

        done.await();

        tchannel.shutdown(false);
    }
}
