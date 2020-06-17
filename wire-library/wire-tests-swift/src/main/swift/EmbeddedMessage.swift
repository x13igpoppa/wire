// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: packed_encoding.proto
import Foundation
import Wire

public struct EmbeddedMessage : Equatable, Proto2Codable, Codable {

  public var inner_repeated_number: [Int32]
  public var inner_number_after: Int32?
  public let unknownFields: Data

  public init(from reader: ProtoReader) throws {
    var inner_repeated_number: [Int32] = []
    var inner_number_after: Int32? = nil

    let unknownFields = try reader.forEachTag { tag in
      switch tag {
        case 1: try reader.decode(into: &inner_repeated_number)
        case 2: inner_number_after = try reader.decode(Int32.self)
        default: try reader.readUnknownField(tag: tag)
      }
    }

    self.inner_repeated_number = try EmbeddedMessage.checkIfMissing(inner_repeated_number, "inner_repeated_number")
    self.inner_number_after = inner_number_after
    self.unknownFields = unknownFields
  }

  public func encode(to writer: ProtoWriter) throws {
    try writer.encode(tag: 1, value: inner_repeated_number, packed: true)
    try writer.encode(tag: 2, value: inner_number_after)
    try writer.writeUnknownFields(unknownFields)
  }

}
