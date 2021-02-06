package org.cs.interview.command

import org.cs.interview.drawing.DrawingPanel


class CommandFactory(private val drawingPanel: DrawingPanel) {

    fun createCommand(command: String): Command {
        val tokens = command.split(" ").filter { it.isNotBlank() }
        val commandType = tokens[0]
        val args = tokens.subList(1, tokens.lastIndex + 1)
        return generateCommand(commandType, args)
    }

    @Throws(InvalidCommandException::class)
    private fun generateCommand(
        commandType: String,
        args: List<String>
    ): Command {
        when (commandType) {
            "C" -> if (args.size == 2) {
                val intValues = convertToIntegers(args)
                return CanvasCommand(drawingPanel, intValues[0], intValues[1])
            }

            "L" -> {
                if (args.size == 4) {
                    val intValues = convertToIntegers(args)
                    return LineCommand(drawingPanel, intValues[0], intValues[1], intValues[2], intValues[3])
                }
            }
            "R" -> {
                if (args.size == 4) {
                    val intValues = convertToIntegers(args)
                    return RectangleCommand(drawingPanel, intValues[0], intValues[1], intValues[2], intValues[3])
                }
            }
            "B" -> {
                if (args.size == 3) {
                    val intValues = convertToIntegers(args.subList(0,2))
                    val char = convertToChar(args[2])
                    return BucketFillCommand(drawingPanel, intValues[0], intValues[1], char)
                }
            }
            "Q" -> return ExitCommand()
            else -> throw InvalidCommandException("Invalid command")
        }
        throw InvalidCommandException("Invalid command")
    }

    @Throws(InvalidCommandException::class)
    private fun convertToIntegers(values: List<String>): List<Int> {
        values.forEach {
            if (!isPositiveInteger(it)) throw InvalidCommandException("Invalid number format")
        }
        return values.map { it.toInt() }
    }

    private fun convertToChar(str: String):Char {
        return str[0]
    }

    private fun isPositiveInteger(str: String): Boolean {
        return try {
            val value = str.toInt()
            value > 0
        } catch (e: NumberFormatException) {
            false
        }
    }
}