package org.cs.interview.command

import org.cs.interview.drawing.DrawingPanel

class CanvasCommand(
    private val drawingPanel: DrawingPanel,
    private val width: Int,
    private val height: Int
) : Command {
    override fun execute() {
        drawingPanel.createNewCanvas(width, height)
        drawingPanel.printCanvas()
    }
}