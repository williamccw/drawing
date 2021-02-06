package org.cs.interview.drawing

class Canvas private constructor(private val board: Array<CharArray>) {

    init {
        for (y in board[0].indices) board[0][y]='-'
        for (x in 1 until board.size) {
            board[x][0] = '|'
            board[x][board[0].size - 1] = '|'
        }
        for (y in board[board.size - 1].indices) board[board.size - 1][y]='-'
    }

    companion object {
        const val MAX_WIDTH = 500
        const val MAX_HEIGHT = 500

        fun create(width: Int, height: Int):Canvas {
            require(width > 0) { "The width must be bigger than zero" }
            require(height > 0) { "The height must be bigger than zero" }
            require(width <= MAX_WIDTH) { "The width excess the system default $MAX_WIDTH" }
            require(height <= MAX_HEIGHT) { "The height excess the system default $MAX_HEIGHT" }
            return Canvas(Array(height + 2) { CharArray(width + 2) { ' ' } })
        }
    }

    val width get() = board[0].size - 2
    val height get() = board.size - 2

    fun validateCoordinate(x: Int, y: Int) {
        require(x > 0) { "Input x must be bigger than zero" }
        require(y > 0) { "Input y must be bigger than zero" }
        require(x <= width) { "Input x excess the system default $MAX_WIDTH" }
        require(y <= height) { "Input y excess the system default $MAX_HEIGHT" }
    }

    fun checkAndPutChar(x: Int, y: Int, char: Char = 'X'): Boolean {
        return if (isEmpty(x, y)) {
            board[y][x] = char
            true
        } else false
    }

    override fun toString() = board.joinToString("\n") { row -> String(row) }

    private fun isEmpty(x: Int, y: Int): Boolean {
        return when {
            (x <= 0 || x > width) -> false
            (y <= 0 || y > height) -> false
            (board[y][x] != ' ') -> false
            else -> true
        }
    }
}