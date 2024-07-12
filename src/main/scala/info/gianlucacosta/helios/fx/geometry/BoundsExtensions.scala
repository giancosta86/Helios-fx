package info.gianlucacosta.helios.fx.geometry

import scalafx.geometry.{Bounds, Point2D}

/**
  * Extensions for the Bounds class
  *
  * @param bounds
  */
class BoundsExtensions private[fx](bounds: Bounds) {
  /**
    * Computes the center of the bounds
    *
    * @return The center as a Point2D
    */
  def centerPoint2D: Point2D = new Point2D(
    bounds.minX + bounds.width / 2,
    bounds.minY + bounds.height / 2
  )
}
