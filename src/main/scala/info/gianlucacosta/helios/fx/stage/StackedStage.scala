package info.gianlucacosta.helios.fx.stage

import scalafx.Includes._
import scalafx.stage.{Stage, WindowEvent}

/**
  * Stage that:
  * <ul>
  * <li>when shown, hides its previous window (as defined in the dedicated method)</li>
  * <li>when hidden, shows its previous window back</li>
  * </ul>
  */
trait StackedStage extends Stage {
  /**
    * The previous stage
    *
    * @return
    */
  def previousStage: javafx.stage.Stage

  this.handleEvent(WindowEvent.WindowShowing) {
    (event: WindowEvent) => {
      previousStage.hide()
      event.consume()
    }
  }

  this.handleEvent(WindowEvent.WindowHiding) {
    (event: WindowEvent) => {
      previousStage.show()
      event.consume()
    }
  }
}
