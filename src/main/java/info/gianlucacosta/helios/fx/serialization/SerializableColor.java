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

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Objects;

/**
 * Encapsulates a JavaFX color instance in order to serialize it
 */
public class SerializableColor implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Color fxColor;
    private final double red;
    private final double green;
    private final double blue;
    private final double opacity;

    public SerializableColor(Color source) {
        this.fxColor = source;

        this.red = source.getRed();
        this.green = source.getGreen();
        this.blue = source.getBlue();
        this.opacity = source.getOpacity();
    }

    public Color getFxColor() {
        if (fxColor == null) {
            fxColor = new Color(red, green, blue, opacity);
        }

        return fxColor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SerializableColor) {
            SerializableColor other = (SerializableColor) obj;

            return Objects.equals(getFxColor(), other.getFxColor());
        } else if (obj instanceof Color) {
            Color other = (Color) obj;

            return Objects.equals(getFxColor(), other);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getFxColor().hashCode();
    }

}
