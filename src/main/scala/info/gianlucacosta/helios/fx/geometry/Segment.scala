package info.gianlucacosta.helios.fx.geometry

import scalafx.geometry.Point2D
import scalafx.scene.shape.Line

/**
  * Simplified segment - it includes properties such as startPoint and endPoint
  */
class Segment extends Line {
  def startPoint = new Point2D(startX.value, startY.value)

  def startPoint_=(newPoint: Point2D) = {
    startX = newPoint.x
    startY = newPoint.y
  }

  def endPoint = new Point2D(endX.value, endY.value)

  def endPoint_=(newPoint: Point2D) = {
    endX = newPoint.x
    endY = newPoint.y
  }
}
