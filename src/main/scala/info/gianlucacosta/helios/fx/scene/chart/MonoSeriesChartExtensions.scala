package info.gianlucacosta.helios.fx.scene.chart

import java.io.PrintWriter

import scala.language.implicitConversions
import scalafx.Includes._
import scalafx.scene.chart.{Chart, PieChart, XYChart}


/**
  * Extensions for charts having just one series
  *
  * @param chart
  */
class MonoSeriesChartExtensions private[fx](chart: Chart) {
  /**
    * Returns true if the chart can be exported as comma-separated values (CSV)
    *
    * @return
    */
  def canExportAsCSV: Boolean =
  chart.isInstanceOf[XYChart[_, _]] || chart.isInstanceOf[PieChart]


  /**
    * Exports the chart as comma-separated values (CSV), writing to the target writer
    *
    * @param targetWriter
    */
  def exportAsCSV(targetWriter: PrintWriter): Unit = {
    chart match {
      case xyChart: XYChart[_, _] =>
        targetWriter.println(s"${xyChart.getXAxis.getLabel},${xyChart.getYAxis.getLabel}")

        xyChart
          .getData
          .get(0)
          .getData
          .foreach(dataItem => {
            targetWriter.println(s"${dataItem.getXValue},${dataItem.getYValue}")
          })


      case pieChart: PieChart =>
        targetWriter.println(s"Item,Value")

        pieChart
          .getData
          .foreach(dataItem => {
            targetWriter.println(s"${dataItem.getName},${dataItem.getPieValue}")
          })


      case _ =>
        throw new UnsupportedOperationException("Unsupported chart type")
    }
  }
}
