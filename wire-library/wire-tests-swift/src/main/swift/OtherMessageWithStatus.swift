// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: same_name_enum.proto
import Foundation
import Wire

public struct OtherMessageWithStatus : Equatable, Proto2Codable, Codable {

  public let unknownFields: Data

  public init(from reader: ProtoReader) throws {

    let unknownFields = try reader.forEachTag { tag in
      switch tag {
        default: try reader.readUnknownField(tag: tag)
      }
    }

    self.unknownFields = unknownFields
  }

  public func encode(to writer: ProtoWriter) throws {
    try writer.writeUnknownFields(unknownFields)
  }

  public enum Status : UInt32, CaseIterable, Codable {

    case A = 1

  }

}
