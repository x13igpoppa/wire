package com.squareup.wire

data class OneOf<T: Any>(
  val key: Key<T>,
  val value: T
) {
  fun <R : Any> get(key: Key<R>): R? {
    if (this.key == key) return value as R
    return null
  }

  data class Key<R>(
    val tag: Int,
    val adapter: ProtoAdapter<R>,
    val redacted: Boolean = false,
    val generatedName: String,
    val declaredName: String = "",
    val jsonName: String = "",
  )
}
