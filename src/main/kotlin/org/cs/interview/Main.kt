package org.cs.interview

import org.cs.interview.command.CommandFactory
import org.cs.interview.drawing.Canvas
import org.cs.interview.drawing.DrawingPanel
import java.util.*

fun main(args: Array<String>) {
    val commandFactory = CommandFactory(DrawingPanel())
    Scanner(System.`in`).use { scanner ->
        while(true) {
            try {
                print("enter command: ")
                commandFactory.createCommand(scanner.nextLine()).execute()
                println()
            } catch ( e: Exception) {
                println(e.message)
            }
        }
    }

}

