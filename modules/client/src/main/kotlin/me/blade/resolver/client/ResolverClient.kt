package me.blade.resolver.client

import me.blade.resolver.client.graphics.window.DisplayMode
import me.blade.resolver.client.graphics.RenderMain
import me.blade.resolver.client.graphics.window.Window
import org.lwjgl.glfw.GLFW.*

object ResolverClient {
    val window = Window()

    @JvmStatic
    fun main(args: Array<String>) {
        window.initialize()

        window.setDisplayMode(DisplayMode.Windowed)
        window.setAttrib(GLFW_VISIBLE, GLFW_TRUE)

        while (!window.shouldClose()) {
            window.pollEvents()
            RenderMain.render()

            window.swapBuffers()
        }

        window.terminate()
    }
}