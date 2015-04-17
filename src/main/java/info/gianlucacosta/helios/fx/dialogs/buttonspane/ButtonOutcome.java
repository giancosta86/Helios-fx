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

import java.util.Objects;

/**
 * An outcome produced by clicking a button
 */
public class ButtonOutcome {

    private final String code;

    /**
     * Creates the outcome
     *
     * @param code Can be any nonempty string, and identifies the outcome
     */
    public ButtonOutcome(String code) {
        Objects.requireNonNull(code);

        if (code.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ButtonOutcome)) {
            return false;
        }

        ButtonOutcome other = (ButtonOutcome) obj;
        return Objects.equals(code, other.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return code;
    }

}
