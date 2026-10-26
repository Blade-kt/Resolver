package me.blade.resolver.client.graphics

import org.lwjgl.opengl.GL11C.*

object RenderMain {
    fun render() {
        glClearColor(1f, 0f, 0f, 1f)
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
    }
}