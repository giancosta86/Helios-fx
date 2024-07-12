package info.gianlucacosta.helios.fx

import javafx.stage.Stage

import info.gianlucacosta.helios.apps.AppInfo

package object application {
  private[application] type AppStartupCallback = (AppInfo, SplashStage, Stage) => Unit
}
