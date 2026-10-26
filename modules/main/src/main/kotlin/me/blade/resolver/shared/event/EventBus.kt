package me.blade.resolver.shared.event

import java.util.Collections

class EventBus <E: Event> {
    private val subscribedListeners = Collections.synchronizedSet(mutableSetOf<EventListener<E>>())

    fun <T: E> post(context: EventContextevent: T) {
        subscribedListeners.forEach {
            it.post(event)
        }
    }

    fun createListener(subscribe: Boolean): EventListener<E> {
        val listener = EventListener<E>()
        if (subscribe) {
            this += listener
        }
        return listener
    }

    operator fun plusAssign(listener: EventListener<E>) {
        subscribedListeners.add(listener)
    }

    operator fun minusAssign(listener: EventListener<E>) {
        subscribedListeners.remove(listener)
    }
}