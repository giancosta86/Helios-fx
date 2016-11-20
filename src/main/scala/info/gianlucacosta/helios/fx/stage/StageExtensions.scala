/*^
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

package info.gianlucacosta.helios.fx.stage

import info.gianlucacosta.helios.apps.AppInfo

import scala.collection.JavaConversions._
import scala.language.implicitConversions
import scalafx.stage.Stage

/**
  * Stage-related utility functions
  *
  * @param stage
  */
class StageExtensions private[fx](stage: Stage) {
  /**
    * Assigns the given stage several icon sizes of the application's main icon
    *
    * @param appInfo The AppInfo object for the application
    */
  def setMainIcon(appInfo: AppInfo): Unit = {
    val iconImages = Seq(16, 32, 64, 128).map(size =>
      appInfo.getMainIconImage(size)
    )

    stage.getIcons.setAll(iconImages)
  }
}
