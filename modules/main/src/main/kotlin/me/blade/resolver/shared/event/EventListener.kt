package me.blade.resolver.shared.event

import kotlin.jvm.java

class EventListener <E: Event> {
    val eventMap = hashMapOf<Class<out E>, MutableSet<EventCallback>>()

    fun <T: E> post(event: T) {
        eventMap[event.javaClass]?.forEach {
            it.function(event)
        }
    }

    inline fun <reified T: E> register(noinline callback: (T) -> Unit) {
        val container = eventMap.getOrPut(T::class.java) { mutableSetOf() }

        container.add(EventCallback { event ->
            callback.invoke(event as T)
        })
    }
}