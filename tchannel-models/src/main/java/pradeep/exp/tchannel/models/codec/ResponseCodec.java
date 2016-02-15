package pradeep.exp.tchannel.models.codec;

import com.google.flatbuffers.FlatBufferBuilder;
import pradeep.exp.tchannel.models.fb.Response;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by pradeep on 01/02/16.
 */
public class ResponseCodec {

    public byte[] encode(int statusCode, String status, byte[] response) {
        FlatBufferBuilder fbb = new FlatBufferBuilder(1024);

        int status_off  = fbb.createString(status);
        int body_off    = Response.createBodyVector(fbb, response);

        int response_off = Response.createResponse(fbb, statusCode, status_off, body_off);
        Response.finishResponseBuffer(fbb, response_off);

        int buffer_start = fbb.dataBuffer().position();
        int buffer_length = fbb.offset();
        byte[] full_buffer = fbb.dataBuffer().array();
        return Arrays.copyOfRange(full_buffer, buffer_start, buffer_start + buffer_length);
    }

    public Response decode(byte[] response) {
        ByteBuffer bb = ByteBuffer.wrap(response);
        return Response.getRootAsResponse(bb);
    }

}
