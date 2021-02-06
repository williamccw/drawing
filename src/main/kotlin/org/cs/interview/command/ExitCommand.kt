package org.cs.interview.command

import kotlin.system.exitProcess

class ExitCommand:Command {
    override fun execute() {
        exitProcess(0)
    }
}