package org.cs.interview.command

import org.cs.interview.drawing.Canvas
import org.cs.interview.drawing.DrawingPanel

class LineCommand(
    private val drawingPanel: DrawingPanel,
    private val startX:Int,
    private val startY:Int,
    private val endX:Int,
    private val endY:Int):Command {

    override fun execute() {
        drawingPanel.drawLine(startX,startY, endX, endY)
        drawingPanel.printCanvas()
    }
}