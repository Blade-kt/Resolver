package me.blade.resolver.client.graphics

import me.blade.resolver.client.graphics.vao.VertexArray
import me.blade.resolver.shared.logger.Logger

class ResourceFactory {
    val logger = Logger("Resources")

    fun createVertexArray(): VertexArray {
        return VertexArray(this)
    }
}