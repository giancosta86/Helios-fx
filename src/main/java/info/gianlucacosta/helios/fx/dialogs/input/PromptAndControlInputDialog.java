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

import info.gianlucacosta.helios.fx.dialogs.NodeDialog;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraintsBuilder;

/**
 * A dialog showing a prompt label and an input control
 */
public abstract class PromptAndControlInputDialog<TValue, TInputControl extends Node> extends NodeDialog<TValue> {

    private final Label promptLabel;
    private final TValue initialValue;

    private TInputControl inputControl;

    public PromptAndControlInputDialog(String title, String prompt, TValue initialValue) {
        super(title);

        promptLabel = new Label(prompt);
        this.initialValue = initialValue;
    }

    @Override
    protected Node createContentNode() {
        GridPane inputPane = new GridPane();
        inputPane.setPadding(new Insets(15, 15, 7.5, 15));

        inputPane.getColumnConstraints().addAll(
                ColumnConstraintsBuilder.create().fillWidth(true).hgrow(Priority.ALWAYS).build());

        inputPane.getRowConstraints().addAll(
                RowConstraintsBuilder.create().valignment(VPos.TOP).minHeight(23).build(),
                RowConstraintsBuilder.create().build());

        inputPane.add(promptLabel, 0, 0);

        inputControl = createInputControl(initialValue);
        inputPane.add(inputControl, 0, 1);

        return inputPane;
    }

    protected abstract TInputControl createInputControl(TValue initialValue);

    protected final TInputControl getInputControl() {
        return inputControl;
    }

    public String getPrompt() {
        return promptLabel.getText();
    }

    public void setPrompt(String prompt) {
        promptLabel.setText(prompt);
    }

    protected TValue getInitialValue() {
        return initialValue;
    }
}
