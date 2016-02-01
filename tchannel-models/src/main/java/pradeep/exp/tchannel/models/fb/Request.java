// automatically generated, do not modify

package pradeep.exp.tchannel.models.fb;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Request extends Table {
  public static Request getRootAsRequest(ByteBuffer _bb) { return getRootAsRequest(_bb, new Request()); }
  public static Request getRootAsRequest(ByteBuffer _bb, Request obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Request __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String serviceId() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer serviceIdAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public String path() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer pathAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public int timeoutMS() { int o = __offset(8); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int body(int j) { int o = __offset(10); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int bodyLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer bodyAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public Tuple headers(int j) { return headers(new Tuple(), j); }
  public Tuple headers(Tuple obj, int j) { int o = __offset(12); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int headersLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public Tuple params(int j) { return params(new Tuple(), j); }
  public Tuple params(Tuple obj, int j) { int o = __offset(14); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int paramsLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public String callback() { int o = __offset(16); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer callbackAsByteBuffer() { return __vector_as_bytebuffer(16, 1); }

  public static int createRequest(FlatBufferBuilder builder,
      int service_id,
      int path,
      int timeout_m_s,
      int body,
      int headers,
      int params,
      int callback) {
    builder.startObject(7);
    Request.addCallback(builder, callback);
    Request.addParams(builder, params);
    Request.addHeaders(builder, headers);
    Request.addBody(builder, body);
    Request.addTimeoutMS(builder, timeout_m_s);
    Request.addPath(builder, path);
    Request.addServiceId(builder, service_id);
    return Request.endRequest(builder);
  }

  public static void startRequest(FlatBufferBuilder builder) { builder.startObject(7); }
  public static void addServiceId(FlatBufferBuilder builder, int serviceIdOffset) { builder.addOffset(0, serviceIdOffset, 0); }
  public static void addPath(FlatBufferBuilder builder, int pathOffset) { builder.addOffset(1, pathOffset, 0); }
  public static void addTimeoutMS(FlatBufferBuilder builder, int timeoutMS) { builder.addInt(2, timeoutMS, 0); }
  public static void addBody(FlatBufferBuilder builder, int bodyOffset) { builder.addOffset(3, bodyOffset, 0); }
  public static int createBodyVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startBodyVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addHeaders(FlatBufferBuilder builder, int headersOffset) { builder.addOffset(4, headersOffset, 0); }
  public static int createHeadersVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startHeadersVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addParams(FlatBufferBuilder builder, int paramsOffset) { builder.addOffset(5, paramsOffset, 0); }
  public static int createParamsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startParamsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addCallback(FlatBufferBuilder builder, int callbackOffset) { builder.addOffset(6, callbackOffset, 0); }
  public static int endRequest(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishRequestBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

