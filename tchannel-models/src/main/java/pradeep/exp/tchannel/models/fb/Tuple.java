// automatically generated, do not modify

package pradeep.exp.tchannel.models.fb;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class Tuple extends Table {
  public static Tuple getRootAsTuple(ByteBuffer _bb) { return getRootAsTuple(_bb, new Tuple()); }
  public static Tuple getRootAsTuple(ByteBuffer _bb, Tuple obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Tuple __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String key() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer keyAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public String value() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer valueAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }

  public static int createTuple(FlatBufferBuilder builder,
      int key,
      int value) {
    builder.startObject(2);
    Tuple.addValue(builder, value);
    Tuple.addKey(builder, key);
    return Tuple.endTuple(builder);
  }

  public static void startTuple(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addKey(FlatBufferBuilder builder, int keyOffset) { builder.addOffset(0, keyOffset, 0); }
  public static void addValue(FlatBufferBuilder builder, int valueOffset) { builder.addOffset(1, valueOffset, 0); }
  public static int endTuple(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

