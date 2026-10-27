package me.blade.resolver.client.graphics.window

import me.blade.resolver.client.events.WindowEvent
import me.blade.resolver.shared.GameConstants
import me.blade.resolver.shared.event.EventBus
import me.blade.resolver.shared.logger.Logger
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWDropCallback
import org.lwjgl.opengl.GL
import kotlin.math.log
import kotlin.properties.Delegates

class Window {
    private var handle by Delegates.notNull<Long>()
    private val logger = Logger("Window")

    val eventBus = EventBus<WindowEvent>()

    init {
        logger.log(Logger.Level.Info, "glfwInit")
        check(glfwInit()) {
            "Unable to initialize GLFW"
        }

        glfwWindowHint(GLFW_VERSION_MAJOR, GL_VERSION.first)
        glfwWindowHint(GLFW_VERSION_MAJOR, GL_VERSION.second)
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)

        logger.log(Logger.Level.Info, "glfwCreateWindow")
        handle = glfwCreateWindow(1, 1, GameConstants.name, 0L, 0L)

        logger.log(Logger.Level.Info, "Initializing GL ${GL_VERSION.first}.${GL_VERSION.second} context")
        glfwMakeContextCurrent(handle)
        GL.createCapabilities()

        logger.log(Logger.Level.Info, "glfwSet*Callback")
        glfwSetKeyCallback(handle) { _, key, sc, action, mods ->
            eventBus.post(WindowEvent.Key(key, sc, action, mods))
        }

        glfwSetCharCallback(handle) { _, cp ->
            eventBus.post(WindowEvent.Char(cp))
        }

        glfwSetMouseButtonCallback(handle) { _, button, action, mods ->
            eventBus.post(WindowEvent.MouseButton(button, action, mods))
        }

        glfwSetCursorPosCallback(handle) { _, x, y ->
            eventBus.post(WindowEvent.CursorPos(x, y))
        }

        glfwSetCursorEnterCallback(handle) { _, entered ->
            eventBus.post(WindowEvent.CursorEnter(entered))
        }

        glfwSetScrollCallback(handle) { _, dx, dy ->
            eventBus.post(WindowEvent.Scroll(dx, dy))
        }

        glfwSetWindowSizeCallback(handle) { _, w, h ->
            eventBus.post(WindowEvent.WindowSize(w, h))
        }

        glfwSetFramebufferSizeCallback(handle) { _, w, h ->
            eventBus.post(WindowEvent.FramebufferSize(w, h))
        }

        glfwSetWindowCloseCallback(handle) { _ ->
            eventBus.post(WindowEvent.WindowClose())
        }

        glfwSetWindowFocusCallback(handle) { _, focused ->
            eventBus.post(WindowEvent.WindowFocus(focused))
        }

        glfwSetWindowIconifyCallback(handle) { _, iconified ->
            eventBus.post(WindowEvent.WindowIconify(iconified))
        }

        glfwSetWindowMaximizeCallback(handle) { _, maximized ->
            eventBus.post(WindowEvent.WindowMaximize(maximized))
        }

        glfwSetWindowContentScaleCallback(handle) { _, xs, ys ->
            eventBus.post(WindowEvent.WindowContentScale(xs.toDouble(), ys.toDouble()))
        }

        glfwSetWindowPosCallback(handle) { _, x, y ->
            eventBus.post(WindowEvent.WindowPos(x, y))
        }

        glfwSetWindowRefreshCallback(handle) { _ ->
            eventBus.post(WindowEvent.WindowRefresh())
        }

        glfwSetDropCallback(handle) { _, count, names ->
            val paths = ArrayList<String>(count)
            for (i in 0 until count) {
                paths += GLFWDropCallback.getName(names, i)
            }
            eventBus.post(WindowEvent.Drop(paths))
        }
    }

    fun setAttrib(key: Int, value: Int) {
        when (key) {
            GLFW_VISIBLE -> {
                if (value == GLFW_TRUE) {
                    logger.log(Logger.Level.Debug, "glfwShowWindow")
                    glfwShowWindow(handle)
                } else {
                    logger.log(Logger.Level.Debug, "glfwHideWindow")
                    glfwHideWindow(handle)
                }
            }
            else -> {
                logger.log(Logger.Level.Debug, "WindowsetAttrib($key, $value)")
                glfwSetWindowAttrib(handle, key, value)
            }
        }
    }

    fun setDisplayMode(mode: DisplayMode) {
        logger.log(Logger.Level.Debug, "setDisplayMode(${mode.name})")
        val monitor = glfwGetPrimaryMonitor()
        val videoMode = glfwGetVideoMode(monitor) ?: throw IllegalStateException("Unable to get video mode")

        when (mode) {
            DisplayMode.Windowed -> {
                val desiredSize = (videoMode.width() / 1.5).toInt() to (videoMode.height() / 1.5).toInt()

                glfwSetWindowMonitor(
                    handle,
                    0L,
                    videoMode.width() / 2 - desiredSize.first / 2, videoMode.height() / 2 - desiredSize.second / 2,
                    desiredSize.first, desiredSize.second,
                    0
                )
            }
            DisplayMode.Borderless -> {
                glfwSetWindowMonitor(handle, 0L, 0, 0, videoMode.width(), videoMode.height(), 0)
            }
            DisplayMode.Fullscreen -> {
                glfwSetWindowMonitor(handle, monitor, 0, 0, videoMode.width(), videoMode.height(), videoMode.refreshRate())
            }
        }
    }

    fun terminate() {
        logger.log(Logger.Level.Info, "terminate")
        glfwDestroyWindow(handle)
        glfwTerminate()
    }

    fun pollEvents() {
        glfwPollEvents()
    }

    fun swapBuffers() {
        glfwSwapBuffers(handle)
    }

    fun shouldClose(): Boolean {
        return glfwWindowShouldClose(handle)
    }

    companion object {
        private val GL_VERSION = 4 to 6
    }
}