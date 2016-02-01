package pradeep.exp.tchannel;

import com.uber.tchannel.api.handlers.RawRequestHandler;
import com.uber.tchannel.messages.RawRequest;
import com.uber.tchannel.messages.RawResponse;

/**
 * Created by pradeep on 31/01/16.
 */
public class FooRequestHandler extends RawRequestHandler {

    @Override
    public RawResponse handleImpl(RawRequest rawRequest) {

        System.out.println("INCOMING:" + rawRequest.getService() + ":" + rawRequest.getBody() + ":" + rawRequest.getHeader());

        return new RawResponse.Builder(rawRequest)
                .setBody(rawRequest.getBody())
                .setHeader(rawRequest.getHeader())
                .build();
    }

}
