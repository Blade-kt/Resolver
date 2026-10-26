package me.blade.resolver.shared.event

import javax.naming.event.EventContext

class EventCallback(
    val contextClass: Class<out Any>?,
    val function: EventContext.(Event) -> Unit
)