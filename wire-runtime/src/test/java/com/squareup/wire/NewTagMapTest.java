/*
 * Copyright 2015 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire;

import com.google.protobuf.FieldDescriptorProto.Type;
import com.google.protobuf.FileOptions;
import java.util.Arrays;
import java.util.List;
import okio.ByteString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NewTagMapTest {
  final Extension<FileOptions, Double> extensionA
      = Extension.doubleExtending(FileOptions.class)
      .setName("a")
      .setTag(1)
      .buildOptional();
  final Extension<FileOptions, String> extensionB
      = Extension.stringExtending(FileOptions.class)
      .setName("b")
      .setTag(2)
      .buildOptional();
  final Extension<FileOptions, Type> extensionC
      = Extension.enumExtending(Type.class, FileOptions.class)
      .setName("c")
      .setTag(3)
      .buildOptional();
  final Extension<FileOptions, List<Double>> extensionD
      = Extension.doubleExtending(FileOptions.class)
      .setName("d")
      .setTag(4)
      .buildPacked();
  final Extension<FileOptions, List<String>> extensionE
      = Extension.stringExtending(FileOptions.class)
      .setName("e")
      .setTag(5)
      .buildRepeated();

  @Test public void putAndGetExtensionValues() throws Exception {
    NewTagMap tagMap = new NewTagMap();
    tagMap.add(extensionA, 3.14159);
    tagMap.add(extensionB, "hello");
    tagMap.add(extensionC, Type.TYPE_SINT64);
    assertThat(tagMap.get(extensionA)).isEqualTo(3.14159);
    assertThat(tagMap.get(extensionB)).isEqualTo("hello");
    assertThat(tagMap.get(extensionC)).isEqualTo(Type.TYPE_SINT64);
  }

  @Test public void equalsAndHashCode() throws Exception {
    NewTagMap tagMap1 = new NewTagMap();
    tagMap1.add(extensionA, 3.14159);
    tagMap1.add(extensionB, "hello");

    NewTagMap tagMap2 = new NewTagMap();
    tagMap2.add(extensionA, 3.14159);
    tagMap2.add(extensionB, "hello");

    NewTagMap tagMap3 = new NewTagMap();

    NewTagMap tagMap4 = new NewTagMap();
    tagMap4.add(extensionC, Type.TYPE_SINT64);

    assertThat(tagMap1.equals(tagMap1)).isTrue();
    assertThat(tagMap1.equals(tagMap2)).isTrue();
    assertThat(tagMap1.equals(tagMap3)).isFalse();
    assertThat(tagMap1.equals(tagMap4)).isFalse();

    assertThat(tagMap3.equals(tagMap1)).isFalse();
    assertThat(tagMap3.equals(tagMap3)).isTrue();
    assertThat(tagMap3.equals(tagMap4)).isFalse();

    assertThat(tagMap4.equals(tagMap1)).isFalse();
    assertThat(tagMap4.equals(tagMap3)).isFalse();
    assertThat(tagMap4.equals(tagMap4)).isTrue();

    assertThat(tagMap1.hashCode()).isEqualTo(tagMap2.hashCode());
    assertThat(tagMap1.hashCode()).isNotEqualTo(tagMap3.hashCode());
    assertThat(tagMap1.hashCode()).isNotEqualTo(tagMap4.hashCode());
    assertThat(tagMap3.hashCode()).isNotEqualTo(tagMap4.hashCode());
  }

  @Test public void putAndGetRepeatedValues() throws Exception {
    NewTagMap tagMap = new NewTagMap();
    tagMap.add(extensionD, 1.0);
    tagMap.add(extensionD, 2.0);
    tagMap.add(extensionD, 3.0);
    tagMap.add(extensionE, "hacker");
    tagMap.add(extensionE, "slacker");
    tagMap.add(extensionE, "cracker");
    assertThat(tagMap.get(extensionD)).isEqualTo(Arrays.asList(1.0, 2.0, 3.0));
    assertThat(tagMap.get(extensionE)).isEqualTo(Arrays.asList("hacker", "slacker", "cracker"));
  }

  /** Confirm that the implementation doubles from 8 to 16 and 16 to 32 elements. */
  @Test public void manyRepeatedValues() throws Exception {
    NewTagMap tagMap = new NewTagMap();
    tagMap.add(extensionD, 1.0);
    tagMap.add(extensionD, 2.0);
    tagMap.add(extensionD, 3.0);
    tagMap.add(extensionD, 4.0);
    tagMap.add(extensionD, 5.0);
    tagMap.add(extensionD, 6.0);
    tagMap.add(extensionD, 7.0);
    tagMap.add(extensionD, 8.0);
    tagMap.add(extensionD, 9.0);
    tagMap.add(extensionD, 10.0);
    tagMap.add(extensionD, 11.0);
    tagMap.add(extensionD, 12.0);
    tagMap.add(extensionD, 13.0);
    tagMap.add(extensionD, 14.0);
    tagMap.add(extensionD, 15.0);
    tagMap.add(extensionD, 16.0);
    tagMap.add(extensionD, 17.0);
    assertThat(tagMap.get(extensionD)).isEqualTo(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0,
        8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0));
  }

  @Test public void rawToExtensionConversionForDouble() throws Exception {
    NewTagMap tagMap = new NewTagMap();
    tagMap.add(1, FieldEncoding.FIXED64, 4614256650576692846L);
    assertThat(tagMap.get(extensionA)).isEqualTo(3.14159);
  }

  @Test public void rawToExtensionConversionForString() throws Exception {
    NewTagMap tagMap = new NewTagMap();
    tagMap.add(2, FieldEncoding.LENGTH_DELIMITED, ByteString.encodeUtf8("hello"));
    assertThat(tagMap.get(extensionB)).isEqualTo("hello");
  }

  @Test public void rawToExtensionConversionForEnum() throws Exception {
    NewTagMap tagMap = new NewTagMap();
    tagMap.add(3, FieldEncoding.VARINT, 18L);
    assertThat(tagMap.get(extensionC)).isEqualTo(Type.TYPE_SINT64);
  }

  @Test public void rawToExtensionConversionForUnknownEnum() throws Exception {
    NewTagMap tagMap = new NewTagMap();
    tagMap.add(3, FieldEncoding.VARINT, 2828L);
    assertThat(tagMap.get(extensionC)).isEqualTo(2828);
  }
}