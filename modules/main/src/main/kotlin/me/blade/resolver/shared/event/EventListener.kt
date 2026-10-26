package me.blade.resolver.shared.event

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentSkipListSet
import kotlin.jvm.java

class EventListener {
    val eventMap = ConcurrentHashMap<Class<out Event>, ConcurrentSkipListSet<(Event) -> Unit>>()

    fun <T: Event> post(event: T) {
        eventMap[event.javaClass]?.forEach {
            it(event)
        }
    }

    inline fun <reified T: Event> register(noinline callback: (T) -> Unit) {
        val container = eventMap.getOrPut(T::class.java) {
            ConcurrentSkipListSet<(Event) -> Unit>()
        }

        @Suppress("UNCHECKED_CAST")
        container.add(callback as ((Event) -> Unit)?)
    }
}