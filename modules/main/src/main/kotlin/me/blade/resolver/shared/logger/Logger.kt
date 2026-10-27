package me.blade.resolver.shared.logger

import java.io.PrintStream

class Logger(private val label: String) {
    enum class Level(val printStream: () -> PrintStream, val color: String) {
        Error(System::err, ANSI_RED),
        Warn(System::out, ANSI_YELLOW),
        Info(System::out, ANSI_RESET),
        Debug(System::out, ANSI_CYAN)
    }

    fun log(level: Level, message: String) {
        if (level.ordinal > VERBOSE_LEVEL.ordinal) return
        val line = "[$label - ${level.name}]: $message"

        level.printStream().println("${level.color}$line$ANSI_RESET")
    }

    companion object {
        var VERBOSE_LEVEL = Level.Info
        private const val ANSI_RESET = "\u001B[0m"
        private const val ANSI_RED = "\u001B[31m"
        private const val ANSI_YELLOW = "\u001B[33m"
        //private const val ANSI_GREEN = "\u001B[32m"
        private const val ANSI_CYAN = "\u001B[36m"
    }
}