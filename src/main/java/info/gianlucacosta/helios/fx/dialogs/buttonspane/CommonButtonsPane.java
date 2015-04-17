/*§
  ===========================================================================
  Helios - FX
  ===========================================================================
  Copyright (C) 2013-2015 Gianluca Costa
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

package info.gianlucacosta.helios.fx.dialogs.buttonspane;

import info.gianlucacosta.helios.fx.dialogs.ButtonsPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Buttons pane providing common buttons
 */
public class CommonButtonsPane extends ButtonsPane {

    public CommonButtonsPane(Stage dialogStage) {
        super(dialogStage);
    }

    /**
     * Adds a standard "OK" button
     */
    public void addOkButton() {
        addDefaultButton(new Button("OK"), CommonButtonOutcome.OK);
    }

    /**
     * Adds a standard "Cancel" button
     */
    public void addCancelButton() {
        addCancelButton(new Button("Cancel"), CommonButtonOutcome.CANCEL);
    }

    /**
     * Adds a standard "Yes" button
     */
    public void addYesButton() {
        addCancelButton(new Button("Yes"), CommonButtonOutcome.YES);
    }

    /**
     * Adds a standard "Cancel" button
     */
    public void addNoButton() {
        addCancelButton(new Button("No"), CommonButtonOutcome.NO);
    }

}
