package pradeep.exp.tchannel.models.codec;


import com.google.flatbuffers.FlatBufferBuilder;

import java.nio.ByteBuffer;

public class FlatBufferUtil {
    public static int createString (FlatBufferBuilder fbb, String s) {
        if (null == s)
            return 0;
        return fbb.createString(s);
    }

    public static byte[] byteBufferToArray(ByteBuffer bb, int length) {
        if (bb == null || length <= 0)
            return null;
        byte[] array = new byte[length];
        bb.get(array, 0, length);
        return array;
    }
}
