package me.blade.resolver.client.graphics.vao

import me.blade.resolver.client.graphics.GLObject
import me.blade.resolver.client.graphics.GameRenderer
import me.blade.resolver.client.graphics.ResourceFactory
import me.blade.resolver.shared.logger.Logger
import org.lwjgl.opengl.GL30C.glBindVertexArray
import org.lwjgl.opengl.GL45C.glCreateVertexArrays

class VertexArray(factory: ResourceFactory): GLObject {
    override val id = glCreateVertexArrays()

    init {
        factory.logger.log(
            Logger.Level.Debug,
            "VertexArray created with id: $id"
        )
    }

    companion object {
        private var bound: VertexArray? = null

        fun bind(vao: VertexArray) {
            glBindVertexArray(vao.id)
            bound = vao
        }

        fun unbind() {
            glBindVertexArray(0)
            bound = null
        }
    }
}