package info.gianlucacosta.helios.fx.geometry

import scalafx.geometry.{BoundingBox, Point2D}

/**
  * Bounds object created from the corners on its main diagonal
  *
  * @param originVertex   Either corner on the main diagonal
  * @param oppositeVertex The other corner on the main diagonal
  */
class DiagonalBounds(originVertex: Point2D, oppositeVertex: Point2D) extends BoundingBox(
  math.min(originVertex.x, oppositeVertex.x),
  math.min(originVertex.y, oppositeVertex.y),
  math.abs(oppositeVertex.x - originVertex.x),
  math.abs(oppositeVertex.y - originVertex.y)
)