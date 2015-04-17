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

package info.gianlucacosta.helios.fx.dialogs.input;

import info.gianlucacosta.helios.application.io.CommonQuestionOutcome;
import info.gianlucacosta.helios.exceptions.ValidationException;
import info.gianlucacosta.helios.fx.dialogs.ButtonsPane;
import info.gianlucacosta.helios.fx.dialogs.buttonspane.ButtonOutcome;
import info.gianlucacosta.helios.fx.dialogs.buttonspane.CommonButtonOutcome;
import info.gianlucacosta.helios.fx.dialogs.buttonspane.CommonButtonsPane;
import javafx.stage.Stage;

/**
 * A question dialog showing the "Yes", "No" and "Cancel" buttons
 */
public class YesNoCancelQuestionDialog extends QuestionDialog {

    public YesNoCancelQuestionDialog(String title, String message) {
        super(title, message);
    }

    @Override
    protected ButtonsPane createButtonsPane(Stage stage) {
        CommonButtonsPane buttonsPane = new CommonButtonsPane(stage);
        buttonsPane.addYesButton();
        buttonsPane.addNoButton();
        buttonsPane.addCancelButton();
        return buttonsPane;
    }

    @Override
    protected CommonQuestionOutcome retrieveValue() throws ValidationException {
        ButtonOutcome buttonOutcome = getButtonOutcome();

        if (buttonOutcome == CommonButtonOutcome.YES) {
            return CommonQuestionOutcome.YES;
        } else if (buttonOutcome == CommonButtonOutcome.NO) {
            return CommonQuestionOutcome.NO;
        } else {
            return CommonQuestionOutcome.CANCEL;
        }
    }

}
