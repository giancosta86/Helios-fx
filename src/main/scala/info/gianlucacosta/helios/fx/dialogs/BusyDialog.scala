package info.gianlucacosta.helios.fx.dialogs

import scalafx.Includes._
import scalafx.application.Platform
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.ProgressIndicator
import scalafx.scene.layout.BorderPane
import scalafx.stage.{Modality, Stage, WindowEvent}

/**
  * A modal dialog showing a progress indicator while an action is performed.
  *
  * The dialog cannot be closed by the user.
  *
  * @param owner                 The owner stage
  * @param dialogTitle           The title
  * @param progressIndicatorSize The size (width and height) of the progress indicator
  */
class BusyDialog(owner: Stage, dialogTitle: String, progressIndicatorSize: Double = 150) extends Stage {
  initOwner(owner)
  initModality(Modality.WindowModal)
  title = dialogTitle

  resizable = false

  handleEvent(WindowEvent.WindowCloseRequest) {
    (event: WindowEvent) => {
      event.consume()
    }
  }

  scene = new Scene {
    root = new BorderPane {
      center = new ProgressIndicator {
        prefWidth = progressIndicatorSize
        prefHeight = progressIndicatorSize
        margin = Insets(50)
      }
    }
  }

  centerOnScreen()


  /**
    * Shows the dialog, executing the given action in background.
    *
    * Updates to the JavaFX scene graph require Platform.runLater
    *
    * @param action
    */
  def run(action: => Unit): Unit = {
    new BusyThread(this, action).start()
  }


  private class BusyThread(busyDialog: BusyDialog, action: => Unit) extends Thread {
    setDaemon(true)

    override def run(): Unit = {
      Platform.runLater {
        busyDialog.show()
      }

      try {
        action
      } finally {
        Platform.runLater {
          busyDialog.close()
        }
      }
    }
  }

}