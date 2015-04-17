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

import info.gianlucacosta.helios.conditions.Condition;
import info.gianlucacosta.helios.conditions.SatisfiedCondition;
import info.gianlucacosta.helios.conditions.UnsatisfiedConditionException;
import info.gianlucacosta.helios.exceptions.ValidationException;
import javafx.scene.control.TextField;

/**
 * Input dialog asking for data by using a TextField control
 */
public abstract class SingleLineInputDialog<TValue> extends PromptAndControlInputDialog<TValue, TextField> {

    private final boolean autoTrimInput;
    private final Condition<TValue> valueCondition;

    public SingleLineInputDialog(String title, String prompt, TValue initialValue) {
        this(title, prompt, initialValue, true);
    }

    public SingleLineInputDialog(String title, String prompt, TValue initialValue, boolean autoTrimInput) {
        this(title, prompt, initialValue, autoTrimInput, new SatisfiedCondition<TValue>());
    }

    public SingleLineInputDialog(String title, String prompt, TValue initialValue, boolean autoTrimInput, Condition<TValue> valueCondition) {
        super(title, prompt, initialValue);

        this.autoTrimInput = autoTrimInput;
        this.valueCondition = valueCondition;
    }

    @Override
    protected TextField createInputControl(TValue initialValue) {
        TextField textField = new TextField();

        if (initialValue != null) {
            textField.setText(convertValueToOutput(initialValue));
        }

        return textField;
    }

    @Override
    protected TValue retrieveValue() throws ValidationException {
        String inputValue = getInputControl().getText();

        if (autoTrimInput) {
            inputValue = inputValue.trim();
        }

        TValue convertedValue = convertInputToValue(inputValue);

        try {
            valueCondition.verify(convertedValue);
        } catch (UnsatisfiedConditionException ex) {
            throw new ValidationException(ex.getMessage());
        }

        return convertedValue;
    }

    @Override
    protected void onStageShown() {
        super.onStageShown();

        TextField inputField = getInputControl();

        inputField.selectAll();
        inputField.requestFocus();
    }

    /**
     * Converts the input string to the dialog value
     *
     * @param input the input string
     * @return the dialog value
     * @throws ValidationException if the conversion between the input string
     *                             and the dialog value is not successful
     */
    protected abstract TValue convertInputToValue(String input) throws ValidationException;

    /**
     * Converts a value (for example, the initial value) to a string to be shown
     * in the input field
     *
     * @param value the value to be converted
     * @return the string representation of the value
     */
    protected abstract String convertValueToOutput(TValue value);

}
