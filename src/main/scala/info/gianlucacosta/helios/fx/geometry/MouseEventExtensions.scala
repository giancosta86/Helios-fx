package info.gianlucacosta.helios.fx.geometry

import scalafx.geometry.Point2D
import scalafx.scene.input.MouseEvent

/**
  * MouseEvent extensions
  *
  * @param mouseEvent
  */
class MouseEventExtensions private[fx](mouseEvent: MouseEvent) {
  /**
    * Returns the event's point
    *
    * @return The event's point, as a Point2D
    */
  def point: Point2D = new Point2D(
    mouseEvent.x,
    mouseEvent.y
  )
}
