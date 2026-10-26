package me.blade.resolver.client.graphics

import me.blade.resolver.shared.GameConstants
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import kotlin.properties.Delegates

class Window {
    private var handle by Delegates.notNull<Long>()

    fun initialize() {
        check(glfwInit()) {
            "Unable to initialize GLFW"
        }

        val monitor = glfwGetPrimaryMonitor()
        val videoMode = glfwGetVideoMode(monitor) ?: throw IllegalStateException("Unable to get video mode")
        val (width, height) = videoMode.width() to videoMode.height()

        glfwWindowHint(GLFW_VERSION_MAJOR, GL_VERSION.first)
        glfwWindowHint(GLFW_VERSION_MAJOR, GL_VERSION.second)
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        handle = glfwCreateWindow(width / 2, height / 2, GameConstants.name, 0L, 0L)
        glfwMakeContextCurrent(handle)
        GL.createCapabilities()
    }

    fun setAttrib(key: Int, value: Int) {
        when (key) {
            GLFW_VISIBLE -> {
                if (value == GLFW_TRUE) {
                    glfwShowWindow(handle)
                } else {
                    glfwHideWindow(handle)
                }
            }
            else -> {
                glfwSetWindowAttrib(handle, key, value)
            }
        }
    }

    fun setDisplayMode(mode: DisplayMode) {
        val monitor = glfwGetPrimaryMonitor()
        val videoMode = glfwGetVideoMode(monitor) ?: throw IllegalStateException("Unable to get video mode")

        when (mode) {
            DisplayMode.Windowed -> {
                val desiredSize = (videoMode.width() / 2) to (videoMode.height() / 2)

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