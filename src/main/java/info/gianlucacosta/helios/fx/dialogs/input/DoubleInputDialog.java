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
import info.gianlucacosta.helios.exceptions.ValidationException;

/**
 * Input dialog asking for a double
 */
public class DoubleInputDialog extends SingleLineInputDialog<Double> {

    public DoubleInputDialog(String title, String prompt) {
        super(title, prompt, 0.0);
    }

    public DoubleInputDialog(String title, String prompt, Double initialValue) {
        super(title, prompt, initialValue);
    }

    public DoubleInputDialog(String title, String prompt, Double initialValue, Condition<Double> valueCondition) {
        super(title, prompt, initialValue, true, valueCondition);
    }

    @Override
    protected Double convertInputToValue(String input) throws ValidationException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            throw new ValidationException("Please, enter a floating-point number!");
        }
    }

    @Override
    protected String convertValueToOutput(Double value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }

}
