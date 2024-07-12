package info.gianlucacosta.helios.fx.stage

import info.gianlucacosta.helios.apps.AppInfo

import scala.jdk.CollectionConverters._
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

    stage.getIcons.setAll(iconImages.asJava)
  }
}
