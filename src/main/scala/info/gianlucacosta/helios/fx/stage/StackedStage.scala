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

package info.gianlucacosta.helios.fx.stage

import scalafx.Includes._
import scalafx.stage.{Stage, WindowEvent}

/**
  * Stage that, when hidden, shows its <em>previous stage</em>, as defined in the dedicated method
  */
trait StackedStage extends Stage {
  /**
    * The previous stage, to be automatically shown when this stage is hidden
    * @return
    */
  def previousStage: javafx.stage.Stage

  this.handleEvent(WindowEvent.WindowHidden) {
    (event: WindowEvent) => {
      previousStage.show()
      event.consume()
    }
  }
}
