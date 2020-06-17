// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: map.proto
import Foundation
import Wire

public struct Mappy : Equatable, Proto2Codable, Codable {

  public var things: [String : Thing]
  public let unknownFields: Data

  public init(from reader: ProtoReader) throws {
    var things: [String : Thing] = [:]

    let unknownFields = try reader.forEachTag { tag in
      switch tag {
        case 1: try reader.decode(into: &things)
        default: try reader.readUnknownField(tag: tag)
      }
    }

    self.things = try Mappy.checkIfMissing(things, "things")
    self.unknownFields = unknownFields
  }

  public func encode(to writer: ProtoWriter) throws {
    try writer.encode(tag: 1, value: things)
    try writer.writeUnknownFields(unknownFields)
  }

}
