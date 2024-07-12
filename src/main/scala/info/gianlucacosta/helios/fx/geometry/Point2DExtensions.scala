package info.gianlucacosta.helios.fx.geometry

import scala.language.implicitConversions
import scalafx.geometry.{Dimension2D, Point2D}

/**
  * Generic extensions for the Point2D class
  *
  * @param point
  */
class Point2DExtensions private[fx](point: Point2D) extends Point2D(point.x, point.y) {
  def +(other: Point2D): Point2D = new Point2D(
    x + other.x,
    y + other.y
  )

  def -(other: Point2D): Point2D = new Point2D(
    x - other.x,
    y - other.y
  )

  def *(factor: Double): Point2D = new Point2D(
    x * factor,
    y * factor
  )

  def /(divisor: Double): Point2D = new Point2D(
    x / divisor,
    y / divisor
  )


  /**
    * Returns a new point: (max(0, min(X, width)), max(0, min(Y, height)))
    *
    * @param width  The max X coordinate for the point
    * @param height The max Y coordinate for the point
    * @return
    */
  def clip(width: Double, height: Double): Point2D = new Point2D(
    math.max(
      0,
      math.min(
        x,
        width
      )
    ),

    math.max(
      0,
      math.min(
        y,
        height
      )
    )
  )

  /**
    * Returns a new point, clipped to stay in the given dimension
    *
    * @param dimension
    * @return
    */
  def clip(dimension: Dimension2D): Point2D =
  clip(dimension.width, dimension.height)
}
