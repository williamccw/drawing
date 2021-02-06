package org.cs.interview.drawing

class DrawingPanel(var canvas: Canvas = Canvas.create(1,1)) {

    fun createNewCanvas(width: Int, height: Int) {
        canvas = Canvas.create(width, height)
    }

    fun printCanvas() {
        println(canvas.toString())
    }

    fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int) {
        canvas.validateCoordinate(x1, y1)
        canvas.validateCoordinate(x2, y2)
        when {
            (y1 == y2) ->
                if (x2 > x1)
                    for (x in x1..x2) {
                        canvas.checkAndPutChar(x, y1)
                    }
                else
                    for (x in x2..x1) {
                        canvas.checkAndPutChar(x, y1)
                    }
            (x1 == x2) ->
                if (y2 > y1)
                    for (y in y1..y2) {
                        canvas.checkAndPutChar(x1, y)
                    }
                else
                    for (y in y2..y1) {
                        canvas.checkAndPutChar(x1, y)
                    }
            else -> throw DrawingException("This is not a straight line")
        }
    }

    fun drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int) {
        drawLine(x1, y1, x1, y2)
        drawLine(x1, y2, x2, y2)
        drawLine(x2, y2, x2, y1)
        drawLine(x2, y1, x1, y1)
    }

    fun bucketFill(x: Int, y: Int, char: Char) {
        if (!canvas.checkAndPutChar(x, y, char)) return
        bucketFill(x - 1, y, char)
        bucketFill(x, y + 1, char)
        bucketFill(x + 1, y, char)
        bucketFill(x, y - 1, char)
    }

}