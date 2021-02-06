package org.cs.interview.command

import org.cs.interview.drawing.DrawingPanel

class BucketFillCommand(
    private val drawingPanel: DrawingPanel,
    private val x: Int,
    private val y: Int,
    private val char: Char
) : Command {

    override fun execute() {
        drawingPanel.bucketFill(x, y, char)
        drawingPanel.printCanvas()
    }
}