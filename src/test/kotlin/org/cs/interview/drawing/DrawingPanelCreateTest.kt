package org.cs.interview.drawing

import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test


internal class DrawingPanelCreateTest {

    companion object {
        private const val WIDTH = 5
        private const val HEIGHT = 5

    }

    private var drawingPanel = DrawingPanel()


    @Before
    fun setup() {
        drawingPanel = DrawingPanel(Canvas.create(WIDTH, HEIGHT))
    }


    @Test
    fun `Create new canvas, expect canvas with correct width and height returned`() {
        assertEquals(WIDTH, drawingPanel.canvas.width)
        assertEquals(HEIGHT, drawingPanel.canvas.height)
    }

    @Test
    fun `Print the canvas, expect correct canvas is printed out `() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent));
        drawingPanel.printCanvas()
        assertEquals(
            """
-------
|     |
|     |
|     |
|     |
|     |
-------
        """.trimIndent().trim(), outContent.toString().trim()
        )
    }

    @Test
    fun `Draw a line with the start point which is out of the Canvas, expect exception occur`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawLine(5, -1, 5, 1)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawLine(5, 0, 5, 1)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawLine(-5, 1, 5, 1)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawLine(0, 1, 5, 1)
        }
    }

    @Test
    fun `Draw a line with the end point which is out of the Canvas, expect exception occur`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawLine(5, 1, 5, -1)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawLine(5, 1, 5, 0)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawLine(5, 1, -5, 1)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawLine(5, 1, 0, 1)
        }
    }

    @Test
    fun `Draw a line with the start and end point cannot be formed a line, expect DrawingException occur`() {
        Assertions.assertThrows(DrawingException::class.java) {
            drawingPanel.drawLine(2, 1, 3, 3)
        }
    }

    @Test
    fun `Draw a horizontal line expect, expect normal line displayed`() {
        drawingPanel.drawLine(1, 1, 5, 1)
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent));
        drawingPanel.printCanvas()
        assertEquals(
            """
-------
|XXXXX|
|     |
|     |
|     |
|     |
-------
        """.trimIndent().trim(), outContent.toString().trim()
        )
    }

    @Test
    fun `Draw a vertical line expect, expect normal line displayed`() {
        drawingPanel.drawLine(1, 1, 1, 5)
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent));
        drawingPanel.printCanvas()
        assertEquals(
            """
-------
|X    |
|X    |
|X    |
|X    |
|X    |
-------
        """.trimIndent().trim(), outContent.toString().trim()
        )
    }

    @Test
    fun `Draw a horizontal line cutting through a vertical line, expect normal line displayed`() {
        drawingPanel.drawLine(1, 3, 5, 3)
        drawingPanel.drawLine(3, 1, 3, 5)
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent));
        drawingPanel.printCanvas()
        assertEquals(
            """
-------
|  X  |
|  X  |
|XXXXX|
|  X  |
|  X  |
-------
        """.trimIndent().trim(), outContent.toString().trim()
        )
    }
}
