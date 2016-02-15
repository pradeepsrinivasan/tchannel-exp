package pradeep.exp.tchannel.models.codec;

import com.google.flatbuffers.FlatBufferBuilder;
import pradeep.exp.tchannel.models.fb.Request;
import pradeep.exp.tchannel.models.fb.Tuple;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by pradeep on 01/02/16.
 */
public class RequestCodec {

    public byte[] encode(String serviceId, String path, byte[] dataBody, Map<String, String> headers, Map<String, String> params, String callback, int timeout) {
        FlatBufferBuilder fbb = new FlatBufferBuilder(1024);

        int service_id_off  = FlatBufferUtil.createString(fbb, serviceId);
        int path_off        = FlatBufferUtil.createString(fbb, path);
        int body_off        = Request.createBodyVector(fbb, dataBody);
        int callback_off    = FlatBufferUtil.createString(fbb, callback);

        int[] headers_arr = new int[headers.entrySet().size()];
        {
            int i = 0;
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headers_arr[i] = _encodeTuple(fbb, entry);
                i++;
            }
        }
        int headers_off     = Request.createHeadersVector(fbb, headers_arr);

        int[] params_arr = new int[params.entrySet().size()];
        {
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                params_arr[i] = _encodeTuple(fbb, entry);
                i++;
            }
        }
        int params_off     = Request.createParamsVector(fbb, params_arr);

        int req_off = Request.createRequest(fbb, service_id_off, path_off, timeout, body_off, headers_off, params_off, callback_off);
        Request.finishRequestBuffer(fbb, req_off);

        int buffer_start = fbb.dataBuffer().position();
        int buffer_length = fbb.offset();
        byte[] full_buffer = fbb.dataBuffer().array();
        return Arrays.copyOfRange(full_buffer, buffer_start, buffer_start + buffer_length);
    }

    private int _encodeTuple(FlatBufferBuilder fbb, Map.Entry<String, String> entry) {
        int key_off = FlatBufferUtil.createString(fbb, entry.getKey());
        int value_off = FlatBufferUtil.createString(fbb, entry.getValue());

        return Tuple.createTuple(fbb, key_off, value_off);
    }

    public Request decode(byte[] request) {
        ByteBuffer bb = ByteBuffer.wrap(request);
        return Request.getRootAsRequest(bb);
    }

}
