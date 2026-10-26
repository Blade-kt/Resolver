package me.blade.resolver.shared.event

import kotlin.jvm.java

class EventListener <E: Event> {
    val eventMap = hashMapOf<Class<out E>, MutableSet<EventCallback>>()

    inline fun <reified C: Any, T: E> post(context: C, event: T) {
        eventMap[event.javaClass]?.forEach {
            if (it.contextClass != null && !C::class.java.isAssignableFrom(it.contextClass)) return@forEach
            it.function(context, event)
        }
    }

    inline fun <reified C: Any, reified T: E> register(noinline callback: C.(T) -> Unit) {
        val container = eventMap.getOrPut(T::class.java) { mutableSetOf() }

        container.add(EventCallback(
            contextClass = C::class.java,
        ) { event ->
            callback.invoke(this as C, event as T)
        })
    }
}