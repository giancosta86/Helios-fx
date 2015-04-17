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
 * Input dialog asking for an integer
 */
public class IntegerInputDialog extends SingleLineInputDialog<Integer> {

    public IntegerInputDialog(String title, String prompt) {
        super(title, prompt, 0);
    }

    public IntegerInputDialog(String title, String prompt, Integer initialValue) {
        super(title, prompt, initialValue);
    }

    public IntegerInputDialog(String title, String prompt, Integer initialValue, Condition<Integer> valueCondition) {
        super(title, prompt, initialValue, true, valueCondition);
    }

    @Override
    protected Integer convertInputToValue(String input) throws ValidationException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new ValidationException("Please, enter an integer number!");
        }
    }

    @Override
    protected String convertValueToOutput(Integer value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }

}
