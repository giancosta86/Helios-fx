package info.gianlucacosta.helios.fx.util

import javafx.embed.swing.JFXPanel

/**
  * JavaFX engine's global utilities
  */
object FxEngine {
  /**
    * Initializes the JavaFX rendering toolkit in contexts where
    * an application is not available (for example, tests)
    */
  def initialize(): Unit = {
    new JFXPanel
  }
}
