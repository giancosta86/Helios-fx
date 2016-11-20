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

package info.gianlucacosta.helios.fx.dialogs.about

import javafx.fxml.FXMLLoader

import info.gianlucacosta.helios.apps.AppInfo

import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonBar, ButtonType}

/**
  * Dialog showing the application's information.
  *
  * This class must be instantiated on the GUI thread.
  *
  * @param appInfo an AppInfo object - for example, an instance of AuroraAppInfo
  */
class AboutBox(appInfo: AppInfo) extends Alert(AlertType.None) {
  private val loader: FXMLLoader =
    new FXMLLoader(this.getClass.getResource("AboutBox.fxml"))

  private val root: javafx.scene.layout.Pane =
    loader.load[javafx.scene.layout.Pane]

  private val controller: AboutBoxController =
    loader.getController[AboutBoxController]


  controller.setup(appInfo)

  dialogPane().setContent(root)


  buttonTypes = Seq(
    new ButtonType("OK", ButtonBar.ButtonData.OKDone)
  )

  title = s"About ${appInfo.name}..."
}
