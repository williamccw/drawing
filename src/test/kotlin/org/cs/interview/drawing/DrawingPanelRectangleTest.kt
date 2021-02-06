package org.cs.interview.drawing

import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.fail
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.fail


internal class DrawingPanelRectangleTest {

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
    fun `Draw a rectangle with the start point which is out of the Canvas, expect exception occur`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawRectangle(5, -1, 4, 2)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawRectangle(5, 0, 4, 2)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawRectangle(-5, 1, 4, 2)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawRectangle(0, 1, 4, 2)
        }
    }

    @Test
    fun `Draw a rectangle with the end point which is out of the Canvas, expect exception occur`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawRectangle(1, 1, 5, -1)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawRectangle(1, 1, 5, 0)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawRectangle(1, 1, -5, 1)
        }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            drawingPanel.drawRectangle(1, 1, 10, 10)
        }
    }

    @Test
    fun `Draw a rectangle with the start point equals to end point, expect an only one X occur`() {
        drawingPanel.drawRectangle(1, 1, 1, 1)
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent));
        drawingPanel.printCanvas()
        assertEquals(
            """
-------
|X    |
|     |
|     |
|     |
|     |
-------
        """.trimIndent().trim(), outContent.toString().trim()
        )
    }

    @Test
    fun `Draw a rectangle with the start point and end point in the same horizontal line, expect a line occur`() {
        drawingPanel.drawRectangle(1, 1, 5, 1)
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
    fun `Draw a rectangle with the start point and end point in the same vertical line, expect a line occur`() {
        drawingPanel.drawRectangle(1, 1, 1, 5)
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
    fun `Draw a rectangle, expect normal rectangle printed out`() {
        drawingPanel.drawRectangle(1, 1, 5, 5)
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent));
        drawingPanel.printCanvas()
        assertEquals(
            """
-------
|XXXXX|
|X   X|
|X   X|
|X   X|
|XXXXX|
-------
        """.trimIndent().trim(), outContent.toString().trim()
        )
    }

    @Test
    fun `Draw two rectangles which intercept each other, expect two intercepted rectangles printed out`() {
        drawingPanel.drawRectangle(1, 1, 4, 4)
        drawingPanel.drawRectangle(5, 5, 2, 2)
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent));
        drawingPanel.printCanvas()
        assertEquals(
            """
-------
|XXXX |
|XXXXX|
|XX XX|
|XXXXX|
| XXXX|
-------
        """.trimIndent().trim(), outContent.toString().trim()
        )
    }

}
