// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: percents_in_kdoc.proto
package com.squareup.wire.protos.kotlin

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.WireField
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.hashCode
import kotlin.jvm.JvmField
import okio.ByteString

class Percents(
  /**
   * e.g. "No limits, free to send and just 2.75% to receive".
   */
  @field:WireField(
    tag = 1,
    adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  @JvmField
  val text: String? = null,
  unknownFields: ByteString = ByteString.EMPTY
) : Message<Percents, Percents.Builder>(ADAPTER, unknownFields) {
  override fun newBuilder(): Builder {
    val builder = Builder()
    builder.text = text
    builder.addUnknownFields(unknownFields)
    return builder
  }

  override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is Percents) return false
    return unknownFields == other.unknownFields
        && text == other.text
  }

  override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = text.hashCode()
      super.hashCode = result
    }
    return result
  }

  override fun toString(): String {
    val result = mutableListOf<String>()
    if (text != null) result += """text=$text"""
    return result.joinToString(prefix = "Percents{", separator = ", ", postfix = "}")
  }

  fun copy(text: String? = this.text, unknownFields: ByteString = this.unknownFields): Percents =
      Percents(text, unknownFields)

  class Builder : Message.Builder<Percents, Builder>() {
    @JvmField
    var text: String? = null

    /**
     * e.g. "No limits, free to send and just 2.75% to receive".
     */
    fun text(text: String?): Builder {
      this.text = text
      return this
    }

    override fun build(): Percents = Percents(
      text = text,
      unknownFields = buildUnknownFields()
    )
  }

  companion object {
    @JvmField
    val ADAPTER: ProtoAdapter<Percents> = object : ProtoAdapter<Percents>(
      FieldEncoding.LENGTH_DELIMITED, 
      Percents::class
    ) {
      override fun encodedSize(value: Percents): Int = 
        ProtoAdapter.STRING.encodedSizeWithTag(1, value.text) +
        value.unknownFields.size

      override fun encode(writer: ProtoWriter, value: Percents) {
        ProtoAdapter.STRING.encodeWithTag(writer, 1, value.text)
        writer.writeBytes(value.unknownFields)
      }

      override fun decode(reader: ProtoReader): Percents {
        var text: String? = null
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            1 -> text = ProtoAdapter.STRING.decode(reader)
            else -> reader.readUnknownField(tag)
          }
        }
        return Percents(
          text = text,
          unknownFields = unknownFields
        )
      }

      override fun redact(value: Percents): Percents = value.copy(
        unknownFields = ByteString.EMPTY
      )
    }
  }
}