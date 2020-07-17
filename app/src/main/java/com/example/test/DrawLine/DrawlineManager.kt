package com.example.test.DrawLine


object DrawlineManager {

    val listLine = ArrayList<Line>()

    var deleteLine: Line? = null
    var deletePoint = arrayOf(Point(0f, 0f), Point(0f, 0f))
    var startPoint = Point(0f, 0f)
    var counter = 0
    const val TIME_DELAY = 500L
    var currentPoint = Point(0f, 0f)
    var currentStep = StepPlotLine.Plot

    enum class StepPlotLine {
        Plot,
        DrawOneLine,
        DrawMultipleLine
    }

    fun addPointList(point: Point) {
        currentPoint = point
        when (currentStep) {

            StepPlotLine.Plot -> {
                startPoint = currentPoint
                currentStep = StepPlotLine.DrawOneLine
            }

            StepPlotLine.DrawOneLine -> {
                listLine.add(
                    Line(
                        Point(startPoint.plotX, startPoint.plotY),
                        Point(currentPoint.plotX, currentPoint.plotY)
                    )
                )
                currentStep = StepPlotLine.DrawMultipleLine
            }

            StepPlotLine.DrawMultipleLine -> addLine(getTotalPoint(listLine))
        }
    }

    private fun getTotalPoint(listline: ArrayList<Line>): ArrayList<Point> {
        val points = ArrayList<Point>()
        val dic = HashMap<String, Int>()
        for (i in listline.indices) {

            val point1 = "${listline[i].startPoint.plotX}, ${listline[i].startPoint.plotY}"
            val point2 = "${listline[i].endPoint.plotX}, ${listline[i].endPoint.plotY}"

            if (dic[point1] == null) {
                dic[point1] = i
                points.add(Point(listline[i].startPoint.plotX, listline[i].startPoint.plotY))
            }
            if (dic[point2] == null) {
                dic[point2] = i
                points.add(Point(listline[i].endPoint.plotX, listline[i].endPoint.plotY))
            }
        }
        return points
    }

    private fun addLine(point: ArrayList<Point>) {
        point.forEach { pointer ->
            listLine.add(Line(Point(pointer.plotX, pointer.plotY), Point(currentPoint.plotX, currentPoint.plotY)))
        }
    }

    fun addDeleteLine(point: Point) {
        when (counter) {
            0 -> deletePoint[0] = point
            1 -> {
                deletePoint[1] = point
                deleteLine = Line(
                    Point(deletePoint[0].plotX, deletePoint[0].plotY),
                    Point(deletePoint[1].plotX, deletePoint[1].plotY)
                )
            }
        }
        counter += 1
    }

    fun clearDeleteLine() {
        counter = 0
        deleteLine = null
    }

    fun clearView() {
        listLine.clear()
        currentStep = StepPlotLine.Plot
        clearDeleteLine()
    }


    private fun checkIntersect(normalLine: Line): Boolean {
        deleteLine?.let { deleteline ->
            val max1: Float
            val min1: Float
            val max2: Float
            val min2: Float
            val A1 = normalLine.endPoint.plotY - normalLine.startPoint.plotY
            val B1 = normalLine.startPoint.plotX - normalLine.endPoint.plotX
            val C1 = A1 * normalLine.startPoint.plotX + B1 * normalLine.startPoint.plotY
            val A2 = deleteline.endPoint.plotY - deleteline.startPoint.plotY
            val B2 = deleteline.startPoint.plotX - deleteline.endPoint.plotX
            val C2 = A2 * deleteline.startPoint.plotX + B2 * deleteline.startPoint.plotY
            val denom = A1 * B2 - A2 * B1
            if (denom == 0.0f) {
                return false
            }
            val i = Point((C1 * B2 - C2 * B1) / denom, (A1 * C2 - A2 * C1) / denom)
            if (normalLine.startPoint.plotX > normalLine.endPoint.plotX) {
                max1 = normalLine.startPoint.plotX
                min1 = normalLine.endPoint.plotX

            } else {
                max1 = normalLine.endPoint.plotX
                min1 = normalLine.startPoint.plotX
            }

            if (deleteline.startPoint.plotX > deleteline.endPoint.plotX) {
                max2 = deleteline.startPoint.plotX
                min2 = deleteline.endPoint.plotX

            } else {
                max2 = deleteline.endPoint.plotX
                min2 = deleteline.startPoint.plotX
            }

            if (i.plotX in min1..max1 && i.plotX in min2..max2) {
                return true
            }
        }
        return false
    }

    fun deleteIntersectLine() {
        val listIntersect = ArrayList<Int>()
        var counter = 0
        listLine.forEachIndexed { index, line ->
            if (checkIntersect(line)) {
                listIntersect.add(index)
            }
        }

        listIntersect.forEach {
            listLine.removeAt(it - counter)
            counter += 1
        }

        if (listLine.size == 0) {
            clearView()
        }

        clearDeleteLine()
    }

}


