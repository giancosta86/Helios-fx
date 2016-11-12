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

import javafx.fxml.FXMLLoader
import javafx.scene.Parent

import scalafx.Includes._
import scalafx.scene.Scene


/**
  * Scene greatly simplifying its loading from an FXML file and its CSS styling, which are based on a
  * sensible naming convention.
  *
  * More precisely, given a controller class -for example, <b>org.example.MyController</b>:
  *
  * <ul>
  *   <li>
  *     The FXML file is expected to be the resource <b>org.example.MyScene.fxml</b>
  *   </li>
  *
  *   <li>
  *     Optionally, there can be a CSS file, loaded to style the scene. In the example, it should be the resource <b>org.example.MyScene.css</b>
  *   </li>
  * </ul>
  *
  * @see FxmlController
  *
  * @param controllerClass The underlying controller class, to be instantiated for the scene. It <b>must</b>:
  *                        <ul>
  *                          <li>
  *                            Implement <b>FxmlController</b>
  *                          </li>
  *
  *                          <li>
  *                            Have a class name ending with <i>Controller</i>
  *                          </li>
  *
  *                          <li>
  *                            Expose a no-arg constructor
  *                          </li>
  *                        </ul>
  * @tparam TController The controller type parameter
  * @tparam TRootNode The type of the root node in the FXML document. It usually belongs to the javafx.** package tree
  */
class FxmlScene[TController <: FxmlController, TRootNode <: Parent](controllerClass: Class[TController]) extends Scene {
  private val sceneSimpleName: String = {
    val controllerSimpleName =
      controllerClass.getSimpleName

    require(controllerSimpleName.endsWith("Controller"))

    val simpleNameRoot =
      controllerSimpleName.substring(
        0,
        controllerSimpleName.length - "Controller".length
      )

    simpleNameRoot + "Scene"
  }


  /**
    * The underlying controller instance
    */
  val controller: TController =
    controllerClass.newInstance()


  root =
    loadFxml()


  private def loadFxml(): TRootNode = {
    val sceneResourceName =
      s"${sceneSimpleName}.fxml"

    val sceneResource =
      controllerClass.getResource(sceneResourceName)

    require(
      sceneResource != null,
      s"Scene resource '${sceneResourceName}' not found"
    )


    val loader =
      new FXMLLoader(
        sceneResource
      ) {
        setController(controller)
      }


    loader.load().asInstanceOf[TRootNode]
  }


  controller.scene =
    this


  tryToLoadCss()


  private def tryToLoadCss(): Unit = {
    val cssResourceName =
      s"${sceneSimpleName}.css"

    val cssResource =
      controllerClass.getResource(cssResourceName)

    if (cssResource != null) {
      stylesheets.add(cssResource.toExternalForm)
    }
  }
}
