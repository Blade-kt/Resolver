package me.blade.resolver.client.events

import me.blade.resolver.shared.event.Event

sealed class WindowEvent : Event {
    class Key(val key: Int, val scancode: Int, val action: Int, val mods: Int) : WindowEvent()
    class Char(val codepoint: Int) : WindowEvent()
    class MouseButton(val button: Int, val action: Int, val mods: Int) : WindowEvent()
    class CursorPos(val x: Double, val y: Double) : WindowEvent()
    class CursorEnter(val entered: Boolean) : WindowEvent()
    class Scroll(val xOffset: Double, val yOffset: Double) : WindowEvent()
    class WindowSize(val width: Int, val height: Int) : WindowEvent()
    class FramebufferSize(val width: Int, val height: Int) : WindowEvent()
    class WindowClose : WindowEvent()
    class WindowFocus(val focused: Boolean) : WindowEvent()
    class WindowIconify(val iconified: Boolean) : WindowEvent()
    class WindowMaximize(val maximized: Boolean) : WindowEvent()
    class WindowContentScale(val xScale: Double, val yScale: Double) : WindowEvent()
    class WindowPos(val xPos: Int, val yPos: Int) : WindowEvent()
    class WindowRefresh : WindowEvent()
    class Drop(val paths: List<String>) : WindowEvent()
}