// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: ../wire-runtime/src/test/proto/redacted_test.proto at 55:1
package com.squareup.wire.protos.redacted;

import com.google.protobuf.FieldOptions;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.UnsupportedOperationException;
import okio.ByteString;

public final class RedactedRequired extends Message<RedactedRequired, RedactedRequired.Builder> {
  public static final ProtoAdapter<RedactedRequired> ADAPTER = new ProtoAdapter<RedactedRequired>(FieldEncoding.LENGTH_DELIMITED, RedactedRequired.class) {
    @Override
    public int encodedSize(RedactedRequired value) {
      return ProtoAdapter.STRING.encodedSizeWithTag(1, value.a)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, RedactedRequired value) throws IOException {
      ProtoAdapter.STRING.encodeWithTag(writer, 1, value.a);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public RedactedRequired decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.a(ProtoAdapter.STRING.decode(reader)); break;
          default: {
            FieldEncoding fieldEncoding = reader.peekFieldEncoding();
            Object value = fieldEncoding.rawProtoAdapter().decode(reader);
            builder.addUnknownField(tag, fieldEncoding, value);
          }
        }
      }
      reader.endMessage(token);
      return builder.build();
    }

    @Override
    public RedactedRequired redact(RedactedRequired value) {
      throw new UnsupportedOperationException("Field 'a' is required and cannot be redacted.");
    }
  };

  private static final long serialVersionUID = 0L;

  public static final FieldOptions FIELD_OPTIONS_A = new FieldOptions.Builder()
      .redacted(true)
      .build();

  public static final String DEFAULT_A = "";

  public final String a;

  public RedactedRequired(String a) {
    this(a, ByteString.EMPTY);
  }

  public RedactedRequired(String a, ByteString unknownFields) {
    super(unknownFields);
    this.a = a;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.a = a;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof RedactedRequired)) return false;
    RedactedRequired o = (RedactedRequired) other;
    return equals(unknownFields(), o.unknownFields())
        && equals(a, o.a);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (a != null ? a.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (a != null) builder.append(", a=██");
    return builder.replace(0, 2, "RedactedRequired{").append('}').toString();
  }

  public static final class Builder extends com.squareup.wire.Message.Builder<RedactedRequired, Builder> {
    public String a;

    public Builder() {
    }

    public Builder a(String a) {
      this.a = a;
      return this;
    }

    @Override
    public RedactedRequired build() {
      if (a == null) {
        throw missingRequiredFields(a, "a");
      }
      return new RedactedRequired(a, buildUnknownFields());
    }
  }
}