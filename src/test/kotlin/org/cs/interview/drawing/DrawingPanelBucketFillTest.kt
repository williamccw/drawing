package org.cs.interview.drawing

import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test


internal class DrawingPanelBucketFillTest {

    companion object {
        private const val WIDTH = 5
        private const val HEIGHT = 5

    }

    private var drawingPanel = DrawingPanel()


    @Before
    fun setup() {
        drawingPanel = DrawingPanel(Canvas.create(WIDTH, HEIGHT))
    }

}
