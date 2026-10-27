package me.blade.resolver.client

import me.blade.resolver.client.graphics.GameRenderer
import me.blade.resolver.client.graphics.window.DisplayMode
import me.blade.resolver.client.graphics.window.Window
import me.blade.resolver.shared.logger.Logger
import org.lwjgl.glfw.GLFW.*
import java.lang.foreign.Arena
import javax.swing.Renderer
import kotlin.properties.Delegates

object ResolverClient {
    var window by Delegates.notNull<Window>(); private set
    var renderer by Delegates.notNull<GameRenderer>(); private set

    var memory by Delegates.notNull<Arena>(); private set

    @JvmStatic
    fun main(args: Array<String>) {
        Logger.VERBOSE_LEVEL = Logger.Level.Debug
        memory = Arena.ofShared()

        window = Window()
        renderer = GameRenderer()

        window.setDisplayMode(DisplayMode.Windowed)
        window.setAttrib(GLFW_VISIBLE, GLFW_TRUE)

        while (!window.shouldClose()) {
            window.pollEvents()

            renderer.render()
            window.swapBuffers()
        }

        window.terminate()
    }
}