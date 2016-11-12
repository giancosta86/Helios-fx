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

package info.gianlucacosta.helios.fx.dialogs.about

import javafx.fxml.FXML

import info.gianlucacosta.helios.apps.AppInfo
import info.gianlucacosta.helios.desktop.DesktopUtils
import scalafx.Includes._

/**
  * Automatically instantiated and configured by AboutBox
  */
class AboutBoxController {
  private var appInfo: AppInfo = _

  def setup(appInfo: AppInfo) {
    this.appInfo =
      appInfo

    nameLabel.text =
      appInfo.name

    versionLabel.text =
      s"Version ${appInfo.version}"

    copyrightLabel.text =
      s"Copyright © ${appInfo.copyrightYears} ${appInfo.copyrightHolder}."

    licenseLabel.text =
      "This software is released under the following license:\n" +
        "\n" +
        s"\t${appInfo.license}"


    additionalInfoLabel.text =
      "For further information, please refer to the LICENSE and README files."


    mainIconImageView.setImage(appInfo.getMainIconImage(128))

    if (appInfo.facebookPage == null || appInfo.facebookPage.isEmpty) {
      showFacebookPageButton.visible =
        false
    }
  }


  def showWebsite() {
    DesktopUtils.openBrowser(appInfo.website)
  }


  def showFacebookPage() {
    DesktopUtils.openBrowser(appInfo.facebookPage)
  }


  @FXML
  var nameLabel: javafx.scene.control.Label = _

  @FXML
  var versionLabel: javafx.scene.control.Label = _

  @FXML
  var copyrightLabel: javafx.scene.control.Label = _

  @FXML
  var licenseLabel: javafx.scene.control.Label = _

  @FXML
  var additionalInfoLabel: javafx.scene.control.Label = _

  @FXML
  var showFacebookPageButton: javafx.scene.control.Button = _

  @FXML
  var mainIconImageView: javafx.scene.image.ImageView = _
}