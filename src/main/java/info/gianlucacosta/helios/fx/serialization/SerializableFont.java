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

package info.gianlucacosta.helios.fx.serialization;

import javafx.scene.text.Font;

import java.io.Serializable;
import java.util.Objects;

/**
 * Encapsulates a JavaFX font instance in order to serialize it
 */
public class SerializableFont implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Font fxFont;
    private final String name;
    private final double size;

    public SerializableFont(Font fxFont) {
        this.fxFont = fxFont;

        this.name = fxFont.getName();
        this.size = fxFont.getSize();
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public Font getFxFont() {
        if (fxFont == null) {
            fxFont = new Font(name, size);
        }

        return fxFont;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SerializableFont) {
            SerializableFont other = (SerializableFont) obj;

            return Objects.equals(getFxFont(), other.getFxFont());
        } else if (obj instanceof Font) {
            Font other = (Font) obj;

            return Objects.equals(getFxFont(), other);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getFxFont().hashCode();
    }

}
