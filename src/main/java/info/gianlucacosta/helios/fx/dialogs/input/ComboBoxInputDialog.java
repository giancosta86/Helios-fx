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
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Input dialog asking the user for a choice among multiple items, displayed via
 * a combo box
 */
public class ComboBoxInputDialog<TValue> extends PromptAndControlInputDialog<TValue, ComboBox<TValue>> {

    private final Collection<? extends TValue> sourceValues;
    private final Condition<TValue> valueCondition;

    public ComboBoxInputDialog(String title, String prompt, Collection<? extends TValue> sourceValues) {
        this(title, prompt, sourceValues, null);
    }

    public ComboBoxInputDialog(String title, String prompt, Collection<? extends TValue> sourceValues, TValue initialValue) {
        this(title, prompt, sourceValues, initialValue, new SatisfiedCondition<TValue>());
    }

    public ComboBoxInputDialog(String title, String prompt, Collection<? extends TValue> sourceValues, TValue initialValue, Condition<TValue> valueCondition) {
        super(title, prompt, initialValue);

        this.sourceValues = new ArrayList<>(sourceValues);
        this.valueCondition = valueCondition;
    }

    @Override
    protected ComboBox<TValue> createInputControl(TValue initialValue) {
        ComboBox<TValue> comboBox = new ComboBox<>();

        comboBox.setMaxWidth(Double.MAX_VALUE);

        comboBox.getItems().addAll(sourceValues);

        if (initialValue != null) {
            comboBox.setValue(initialValue);
        }

        return comboBox;
    }

    @Override
    protected void onStageShown() {
        super.onStageShown();

        ComboBox<TValue> comboBox = getInputControl();

        comboBox.requestFocus();
    }

    @Override
    protected TValue retrieveValue() throws ValidationException {
        ComboBox<TValue> comboBox = getInputControl();
        TValue selectedValue = comboBox.getValue();

        try {
            valueCondition.verify(selectedValue);
        } catch (UnsatisfiedConditionException ex) {
            throw new ValidationException(ex.getMessage());
        }

        return selectedValue;
    }

}
