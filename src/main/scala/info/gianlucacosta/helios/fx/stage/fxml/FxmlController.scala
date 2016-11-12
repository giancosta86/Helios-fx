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

package info.gianlucacosta.helios.fx.stage.fxml

import javafx.stage.Stage

import scalafx.scene.Scene

/**
  * Generic controller for a scene loaded from FXML
  */
trait FxmlController {
  /**
    * The related scene, available <i>after</i> the controller's initialization.
    *
    * Implementing classes just need to expose this var, which will be set by the framework
    */
  var scene: Scene

  /**
    * The stage owning the controller-related scene.
    *
    * This property is NOT available in
    * the controller's <i>initialization method</i>, but can be used in
    * event-handling methods
    *
    * @return The controller's stage
    */
  def stage: Stage =
    scene.window().asInstanceOf[Stage]
}
