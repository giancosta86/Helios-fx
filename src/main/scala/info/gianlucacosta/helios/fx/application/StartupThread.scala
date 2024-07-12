package info.gianlucacosta.helios.fx.application

import javafx.stage.Stage

import info.gianlucacosta.helios.apps.AppInfo
import info.gianlucacosta.helios.fx.dialogs.Alerts

import scalafx.application.Platform


private class StartupThread(
                             appInfo: AppInfo,
                             splashStage: SplashStage,
                             primaryStage: Stage,
                             startupCallback: AppStartupCallback
                           ) extends Thread {
  setDaemon(true)

  override def run(): Unit = {
    try {
      startupCallback(appInfo, splashStage, primaryStage)

      Platform.runLater {
        primaryStage.show()
        splashStage.close()
      }
    } catch {
      case ex: Exception =>
        Platform.runLater {
          Alerts.showException(ex, "Startup error")

          System.exit(1)
        }
    }
  }
}