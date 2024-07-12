package info.gianlucacosta.helios.fx

import info.gianlucacosta.helios.fx.geometry.{BoundsExtensions, MouseEventExtensions, Point2DExtensions}
import info.gianlucacosta.helios.fx.scene.NodeExtensions
import info.gianlucacosta.helios.fx.scene.chart.MonoSeriesChartExtensions
import info.gianlucacosta.helios.fx.stage.{FileChooserExtensions, StageExtensions}

import scala.language.implicitConversions
import scalafx.Includes._
import scalafx.geometry.{Bounds, Point2D}
import scalafx.scene.Node
import scalafx.scene.chart.Chart
import scalafx.scene.input.MouseEvent
import scalafx.stage.{FileChooser, Stage}

/**
  * Central point containing all the extensions provided by the library
  */
object Includes {
  implicit def extendBounds(bounds: javafx.geometry.Bounds): BoundsExtensions =
    new BoundsExtensions(bounds)

  implicit def extendBounds(bounds: Bounds): BoundsExtensions =
    new BoundsExtensions(bounds)


  implicit def extendMouseEvent(mouseEvent: javafx.scene.input.MouseEvent): MouseEventExtensions =
    new MouseEventExtensions(mouseEvent)

  implicit def extendMouseEvent(mouseEvent: MouseEvent): MouseEventExtensions =
    new MouseEventExtensions(mouseEvent)


  implicit def extendPoint2D(source: Point2D): Point2DExtensions =
    new Point2DExtensions(source)


  implicit def extendFileChooser(fileChooser: javafx.stage.FileChooser): FileChooserExtensions =
    new FileChooserExtensions(fileChooser)

  implicit def extendFileChooser(fileChooser: FileChooser): FileChooserExtensions =
    new FileChooserExtensions(fileChooser)


  implicit def extendStage(stage: javafx.stage.Stage): StageExtensions =
    new StageExtensions(stage)

  implicit def extendStage(stage: Stage): StageExtensions =
    new StageExtensions(stage)


  implicit def extendChart(chart: javafx.scene.chart.Chart): MonoSeriesChartExtensions =
    new MonoSeriesChartExtensions(chart)

  implicit def extendChart(chart: Chart): MonoSeriesChartExtensions =
    new MonoSeriesChartExtensions(chart)


  implicit def extendNode(node: javafx.scene.Node): NodeExtensions =
    new NodeExtensions(node)

  implicit def extendNode(node: Node): NodeExtensions =
    new NodeExtensions(node)
}
