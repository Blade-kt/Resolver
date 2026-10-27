package me.blade.resolver.client.graphics

import me.blade.resolver.client.graphics.vao.VertexArray
import me.blade.resolver.shared.logger.Logger
import org.lwjgl.opengl.GL11C.*
import kotlin.properties.Delegates

class GameRenderer {
    val logger = Logger("Renderer")
    val factory = ResourceFactory()

    var vao by Delegates.notNull<VertexArray>(); private set

    init {
        logger.log(Logger.Level.Info, "Initializing Renderer")
        vao = factory.createVertexArray()
    }

    fun render() {
        glClearColor(0f, 0f, 0f, 1f)
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
    }
}