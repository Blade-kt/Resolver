package me.blade.resolver.shared.event

import java.util.concurrent.ConcurrentSkipListSet

class EventBus {
    private val subscribedListeners = ConcurrentSkipListSet<EventListener>()

    fun post(event: Event) {
        subscribedListeners.forEach {
            it.post(event)
        }
    }

    operator fun plusAssign(listener: EventListener) {
        subscribedListeners.add(listener)
    }

    operator fun minusAssign(listener: EventListener) {
        subscribedListeners.remove(listener)
    }
}