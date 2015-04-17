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
import info.gianlucacosta.helios.fx.dialogs.IconDialog;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * A question dialog
 */
public abstract class QuestionDialog extends IconDialog<CommonQuestionOutcome> {

    private static final Image QUESTION_IMAGE
            = new Image(QuestionDialog.class.getResourceAsStream("question.png"));
    private final String message;

    public QuestionDialog(String title, String message) {
        super(title, QUESTION_IMAGE);

        this.message = message;
    }

    @Override
    protected Node createMainNode() {
        return new Label(message);
    }

    @Override
    protected boolean shouldRetrieveValue() {
        return true;
    }

}
