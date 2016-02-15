package pradeep.exp.tchannel;

import com.google.common.collect.Maps;
import junit.framework.TestCase;
import pradeep.exp.tchannel.models.codec.RequestCodec;
import pradeep.exp.tchannel.models.fb.Request;

public class RequestCodecTest extends TestCase {

    private static final RequestCodec codec = new RequestCodec();

    public void testSimpleAttributes() throws Exception {

        String originalCallback = "callback";
        int originalTimeout = 100;

        byte[] req = codec.encode("service-id", "path", "abc".getBytes(), Maps.newHashMap(), Maps.newHashMap(), originalCallback, originalTimeout);

        String x = new String(req);

        byte[] bytes1 = x.getBytes();

        Request req1 = codec.decode(req);
        Request req2 = codec.decode(bytes1);

        assertEquals("callback check", req1.callback(), req2.callback());
        assertEquals("callback check", req1.callback(), originalCallback);

        assertEquals("timeout check", req1.timeoutMS(), req2.timeoutMS());
        assertEquals("timeout check", req1.timeoutMS(), originalTimeout);

    }

}
