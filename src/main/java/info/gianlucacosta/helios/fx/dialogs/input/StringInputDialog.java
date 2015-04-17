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
 * Input dialog asking for a single-line string
 */
public class StringInputDialog extends SingleLineInputDialog<String> {

    public StringInputDialog(String title, String prompt) {
        super(title, prompt, null);
    }

    public StringInputDialog(String title, String prompt, String initialValue) {
        super(title, prompt, initialValue);
    }

    public StringInputDialog(String title, String prompt, String initialValue, boolean autoTrimInput) {
        super(title, prompt, initialValue, autoTrimInput);
    }

    public StringInputDialog(String title, String prompt, String initialValue, boolean autoTrimInput, Condition<String> valueCondition) {
        super(title, prompt, initialValue, autoTrimInput, valueCondition);
    }

    @Override
    protected String convertInputToValue(String input) throws ValidationException {
        return input;
    }

    @Override
    protected String convertValueToOutput(String value) {
        if (value == null) {
            return "";
        }

        return value;
    }

}
