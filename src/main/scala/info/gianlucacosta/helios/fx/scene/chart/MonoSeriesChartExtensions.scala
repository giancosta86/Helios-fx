/*§
  ===========================================================================
  Helios - FX
  ===========================================================================
  Copyright (C) 2013-2016 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

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
