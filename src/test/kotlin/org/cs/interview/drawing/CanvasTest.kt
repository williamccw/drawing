package org.cs.interview.drawing

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test


internal class CanvasTest {

    @Test
    fun `Create canvas with negative width, expect exception occur`() {
        assertThrows(IllegalArgumentException::class.java) {
            Canvas.create(-1, 10)
        }
    }

    @Test
    fun `Create canvas with negative height, expect exception occur`() {
        assertThrows(IllegalArgumentException::class.java) {
            Canvas.create(1, -10)
        }
    }

    @Test
    fun `Create canvas with width greater than the limit, expect exception occur`() {
        assertThrows(IllegalArgumentException::class.java) {
            Canvas.create(Canvas.MAX_WIDTH + 1, 1)
        }
    }

    @Test
    fun `Create canvas with height greater than the limit, expect exception occur`() {
        assertThrows(IllegalArgumentException::class.java) {
            Canvas.create(1, Canvas.MAX_HEIGHT + 1)
        }
    }

    @Test
    fun `Create canvas with ZERO width, expect exception occur`() {
        assertThrows(IllegalArgumentException::class.java) {
            Canvas.create(0, 10)
        }
    }

    @Test
    fun `Create canvas with ZERO height, expect exception occur`() {
        assertThrows(IllegalArgumentException::class.java) {
            Canvas.create(10, 0)
        }
    }

    @Test
    fun `Create canvas with width and height equal to the limit, expect canvas created with the width and height equals to limit`() {
        val canvas = Canvas.create(Canvas.MAX_WIDTH, Canvas.MAX_HEIGHT)
        assertEquals(Canvas.MAX_WIDTH, canvas.width)
        assertEquals(Canvas.MAX_HEIGHT, canvas.height)
    }

    @Test
    fun `Create canvas with valid width and height, expect canvas created with the width and height equals to limit`() {
        val canvas = Canvas.create(5, 3)
        assertEquals(5, canvas.width)
        assertEquals(3, canvas.height)
    }

    @Test
    fun `Create and get the toString of the canvas, expect corrected string returned`() {
        var canvas = Canvas.create(1, 1)
        assertEquals(
            """
---
| |
---
            """.trimIndent(), canvas.toString()
        )

        canvas = Canvas.create(1, 3)
        assertEquals(
            """
---
| |
| |
| |
---
            """.trimIndent(), canvas.toString()
        )

        canvas = Canvas.create(3, 1)
        assertEquals(
            """
-----
|   |
-----
            """.trimIndent(), canvas.toString()
        )
    }

    @TestFactory
    fun `given coordinate x y, when validating it in a 3x3 canvas, expect exception thrown`() =
        listOf(
            4 to 1,
            1 to 4,
            -4 to -4,
            1 to -4,
            -4 to 0,
            0 to 4,
            4 to 4
        ).map { (x, y) ->
            dynamicTest(
                "given \"($x\", \"$y)\", expect exception thrown"
            ) {
                val canvas = Canvas.create(3, 3)
                assertThrows(IllegalArgumentException::class.java) {
                    canvas.validateCoordinate(x, y)
                }
            }
        }

    @TestFactory
    fun `given coordinate x y, when check if it is empty it in a 3x3 canvas, expect false returned`() =
        listOf(
            4 to 1,
            1 to 4,
            -4 to -4,
            1 to -4,
            -4 to 0,
            0 to 4,
            4 to 4
        ).map { (x, y) ->
            dynamicTest(
                "given \"($x\", \"$y)\", expect false returned"
            ) {
                val canvas = Canvas.create(3, 3)
                assertFalse(canvas.checkAndPutChar(x, y))
            }
        }

    @Test
    fun `Paint an X which is out of the canvas area, expect false returned`() {
        val canvas = Canvas.create(3, 3)
        assertFalse(canvas.checkAndPutChar(4, 1))
        assertFalse(canvas.checkAndPutChar(1, 4))
        assertFalse(canvas.checkAndPutChar(-4, 1))
        assertFalse(canvas.checkAndPutChar(1, -4))
        assertFalse(canvas.checkAndPutChar(-4, -4))
        assertFalse(canvas.checkAndPutChar(0, 0))
        assertFalse(canvas.checkAndPutChar(4, 4))
    }

    @Test
    fun `Paint a X at four corner, expect canvas's four corner has been filled`() {
        val canvas = Canvas.create(3, 3)
        assertTrue(canvas.checkAndPutChar(1, 1))
        assertTrue(canvas.checkAndPutChar(1, 3))
        assertTrue(canvas.checkAndPutChar(3, 1))
        assertTrue(canvas.checkAndPutChar(3, 3))

        assertEquals(
            """
-----
|X X|
|   |
|X X|
-----
            """.trimIndent(), canvas.toString()
        )
    }

    @Test
    fun `Paint a X into canvas but destination is not empty, expect false returned`() {
        val canvas = Canvas.create(3, 3)
        assertTrue(canvas.checkAndPutChar(2, 1))
        assertFalse(canvas.checkAndPutChar(2, 1))
    }
}