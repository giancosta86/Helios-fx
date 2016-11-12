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

package info.gianlucacosta.helios.fx.scene

import java.io.OutputStream
import javafx.embed.swing.SwingFXUtils
import javax.imageio.ImageIO

import scala.language.implicitConversions
import scalafx.scene.{Node, SnapshotParameters}

/**
  * Generic FX node extensions
  *
  * @param node
  */
class NodeExtensions private[fx](node: Node) {
  /**
    * Exports the node as an image.
    *
    * @param targetStream The target stream
    * @param format       The image format, as a string. Defaults to <i>png</i>
    */
  def exportAsImage(targetStream: OutputStream, format: String = "png"): Unit = {
    val snapshotImage =
      node.snapshot(
        new SnapshotParameters(),
        null
      )

    ImageIO.write(
      SwingFXUtils.fromFXImage(snapshotImage, null),
      format,
      targetStream
    )
  }
}
