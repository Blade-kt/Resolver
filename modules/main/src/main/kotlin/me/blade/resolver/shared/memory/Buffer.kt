package me.blade.resolver.shared.memory

import me.blade.resolver.shared.util.vec.Vec2
import me.blade.resolver.shared.util.vec.Vec2i
import me.blade.resolver.shared.util.vec.Vec3
import me.blade.resolver.shared.util.vec.Vec3i
import me.blade.resolver.shared.util.vec.Vec4
import me.blade.resolver.shared.util.vec.Vec4i
import org.joml.Matrix4f
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class Buffer(private val segment: MemorySegment) {
    var capacity = 0L
    var cursor = 0L

    fun skip(bytes: Int) = write(bytes.toLong()) {}

    fun byte(value: Byte) = write(1) {
        segment.set(ValueLayout.JAVA_BYTE, cursor, value)
    }

    fun short(value: Short) = write(2) {
        segment.set(ValueLayout.JAVA_SHORT_UNALIGNED, cursor, value)
    }

    fun long(value: Long) = write(8) {
        segment.set(ValueLayout.JAVA_LONG_UNALIGNED, cursor, value)
    }

    fun int(value: Int) = write(4) {
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor, value)
    }

    fun float(value: Float) = write(4) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, value)
    }

    fun float(value: Double) = write(4) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, value.toFloat())
    }

    fun ivec2(x: Int, y: Int) = write(8) {
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor, x)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 4, y)
    }

    fun ivec2(vec: Vec2i) = write(8) {
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor, vec.x)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 4, vec.y)
    }

    fun vec2(x: Float, y: Float) = write(8) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, x)
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, y)
    }

    fun vec2(x: Double, y: Double) = write(8) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, x.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, y.toFloat())
    }

    fun vec2(vec: Vec2) = write(8) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, vec.x.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, vec.y.toFloat())
    }

    fun ivec3(x: Int, y: Int, z: Int) = write(12) {
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor, x)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 4, y)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 8, z)
    }

    fun ivec3(vec: Vec3i) = write(12) {
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor, vec.x)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 4, vec.y)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 8, vec.z)
    }

    fun vec3(x: Float, y: Float, z: Float) = write(12) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, x)
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, y)
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 8, z)
    }

    fun vec3(x: Double, y: Double, z: Double) = write(12) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, x.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, y.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 8, z.toFloat())
    }

    fun vec3(vec: Vec3) = write(12) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, vec.x.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, vec.y.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 8, vec.z.toFloat())
    }

    fun ivec4(x: Int, y: Int, z: Int, w: Int) = write(16) {
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor, x)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 4, y)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 8, z)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 12, w)
    }

    fun ivec4(vec: Vec4i) = write(16) {
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor, vec.x)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 4, vec.y)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 8, vec.z)
        segment.set(ValueLayout.JAVA_INT_UNALIGNED, cursor + 12, vec.w)
    }

    fun vec4(x: Float, y: Float, z: Float, w: Float) = write(16) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, x)
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, y)
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 8, z)
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 12, w)
    }

    fun vec4(x: Double, y: Double, z: Double, w: Double) = write(16) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, x.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, y.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 8, z.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 12, w.toFloat())
    }

    fun vec4(vec: Vec4) = write(16) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor, vec.x.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 4, vec.y.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 8, vec.z.toFloat())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 12, vec.w.toFloat())
    }

    fun mat4(value: Matrix4f) = write(64) {
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor     , value.m00())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor +  4, value.m01())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor +  8, value.m02())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 12, value.m03())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 16, value.m10())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 20, value.m11())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 24, value.m12())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 28, value.m13())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 32, value.m20())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 36, value.m21())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 40, value.m22())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 44, value.m23())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 48, value.m30())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 52, value.m31())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 56, value.m32())
        segment.set(ValueLayout.JAVA_FLOAT_UNALIGNED, cursor + 60, value.m33())
    }

    private inline fun write(bytes: Long, block: () -> Unit) {
        block()
        cursor += bytes
    }
}