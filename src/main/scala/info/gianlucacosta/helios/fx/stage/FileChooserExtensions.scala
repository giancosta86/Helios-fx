package info.gianlucacosta.helios.fx.stage

import java.io.File
import javafx.stage.Window

import scala.collection.JavaConversions._
import scala.language.implicitConversions
import scalafx.stage.FileChooser


object FileChooserExtensions {
  private var latestChosenFiles =
    Map[Int, File]()
}


/**
  * Extension methods for FileChooser
  *
  * @param fileChooser
  */
class FileChooserExtensions private[fx](fileChooser: FileChooser) {
  /**
    * Shows the <i>Open</i> dialog in the directory of its latest opened/saved file
    *
    * @param window
    * @return The chosen file, or null
    */
  def smartOpen(window: Window): File = {
    setupInitialDirectory()

    val chosenFile = fileChooser.showOpenDialog(window)
    if (chosenFile == null) {
      return null
    }

    FileChooserExtensions.latestChosenFiles +=
      (System.identityHashCode(fileChooser) -> chosenFile)

    chosenFile
  }


  /**
    * Shows the <i>Save</i> dialog:
    * <ol>
    * <li>In the directory of its latest opened/saved file</li>
    * <li>If no extension is provided, adds the first extension of the current extension filter,
    * unless it ends with * (as in *.*)</li>
    * </ol>
    *
    * @param window
    * @return The chosen file, or null
    */
  def smartSave(window: Window): File = {
    setupInitialDirectory()

    val chosenFile = fileChooser.showSaveDialog(window)
    if (chosenFile == null) {
      return null
    }

    FileChooserExtensions.latestChosenFiles +=
      (System.identityHashCode(fileChooser) -> chosenFile)

    val fileHasExtension =
      chosenFile.getName.split('.').length > 1

    if (!fileHasExtension) {
      val selectedFilter = fileChooser.getSelectedExtensionFilter

      val filterMainExtension =
        selectedFilter.getExtensions
          .head
          .split('.')
          .last

      val defaultExtensionString =
        if (filterMainExtension.endsWith("*"))
          ""
        else
          "." + filterMainExtension

      new File(s"${chosenFile.getAbsolutePath}${defaultExtensionString}")
    } else {
      chosenFile
    }
  }


  private def setupInitialDirectory(): Unit = {
    val latestChosenFileOption = FileChooserExtensions.latestChosenFiles.get(
      System.identityHashCode(fileChooser)
    )

    latestChosenFileOption.foreach(latestChosenFile =>
      fileChooser.initialDirectory = latestChosenFile.getParentFile
    )
  }
}