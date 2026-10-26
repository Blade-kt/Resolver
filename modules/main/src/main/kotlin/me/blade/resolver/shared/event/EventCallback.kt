package me.blade.resolver.shared.event

class EventCallback <T : Event>(val runnable: (T) -> Unit)