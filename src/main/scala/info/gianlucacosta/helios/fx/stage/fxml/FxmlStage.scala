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

import javafx.scene.Parent

import scalafx.stage.Stage

/**
  * Stage whose scene is an FxmlScene
  *
  * @see FxmlController
  * @see FxmlScene
  *
  * @param controllerClass The controller class
  * @tparam TController The controller parameter type
  * @tparam TRootNode The root node in the FXML file
  */
class FxmlStage[TController <: FxmlController, TRootNode <: Parent](controllerClass: Class[TController]) extends Stage {
  /**
    * The FxmlScene instance owned by the stage
    */
  val fxmlScene: FxmlScene[TController, TRootNode] =
    new FxmlScene[TController, TRootNode](controllerClass)

  scene =
    fxmlScene
}
