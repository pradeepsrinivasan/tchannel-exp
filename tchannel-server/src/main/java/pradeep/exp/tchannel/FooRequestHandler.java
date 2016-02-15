package pradeep.exp.tchannel;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.uber.tchannel.api.handlers.RawRequestHandler;
import com.uber.tchannel.messages.RawRequest;
import com.uber.tchannel.messages.RawResponse;

/**
 * Created by pradeep on 31/01/16.
 */
public class FooRequestHandler extends RawRequestHandler {

    private final MetricRegistry registry;
    private final Meter requests;

    public FooRequestHandler(MetricRegistry registry) {
        this.registry = registry;
        requests = registry.meter("foo-rps");
    }

    @Override
    public RawResponse handleImpl(RawRequest rawRequest) {

//        byte[] response = responseCodec.encode(200, "OK", rawRequest.getBody().getBytes(Charset.defaultCharset()));
        requests.mark();

        return new RawResponse.Builder(rawRequest)
                .setBody(rawRequest.getBody())
                .setHeader(rawRequest.getHeader())
                .build();
    }

}
