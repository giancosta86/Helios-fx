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
