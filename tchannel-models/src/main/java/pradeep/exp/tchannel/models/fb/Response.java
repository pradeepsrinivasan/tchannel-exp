// automatically generated, do not modify

package pradeep.exp.tchannel.models.fb;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class Response extends Table {
  public static Response getRootAsResponse(ByteBuffer _bb) { return getRootAsResponse(_bb, new Response()); }
  public static Response getRootAsResponse(ByteBuffer _bb, Response obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Response __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int statusCode() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String status() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer statusAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public int body(int j) { int o = __offset(8); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int bodyLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer bodyAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }

  public static int createResponse(FlatBufferBuilder builder,
      int status_code,
      int status,
      int body) {
    builder.startObject(3);
    Response.addBody(builder, body);
    Response.addStatus(builder, status);
    Response.addStatusCode(builder, status_code);
    return Response.endResponse(builder);
  }

  public static void startResponse(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addStatusCode(FlatBufferBuilder builder, int statusCode) { builder.addInt(0, statusCode, 0); }
  public static void addStatus(FlatBufferBuilder builder, int statusOffset) { builder.addOffset(1, statusOffset, 0); }
  public static void addBody(FlatBufferBuilder builder, int bodyOffset) { builder.addOffset(2, bodyOffset, 0); }
  public static int createBodyVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startBodyVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static int endResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

