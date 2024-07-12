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

  def setup(appInfo: AppInfo): Unit = {
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
  }


  def showWebsite(): Unit = {
    DesktopUtils.openBrowser(appInfo.website)
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
  var mainIconImageView: javafx.scene.image.ImageView = _
}