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

import javafx.geometry.Point2D;

import java.io.Serializable;
import java.util.Objects;

/**
 * Encapsulates a JavaFX point instance in order to serialize it
 */
public class SerializablePoint2D implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Point2D point;
    private double x;
    private double y;

    public SerializablePoint2D(Point2D point) {
        this.point = point;

        x = point.getX();
        y = point.getY();
    }

    public SerializablePoint2D(double x, double y) {
        this(new Point2D(x, y));
    }

    public Point2D getFxPoint() {
        if (point == null) {
            point = new Point2D(x, y);
        }

        return point;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SerializablePoint2D) {

            SerializablePoint2D other = (SerializablePoint2D) obj;
            return Objects.equals(getFxPoint(), other.getFxPoint());

        } else if (obj instanceof Point2D) {

            Point2D other = (Point2D) obj;
            return Objects.equals(getFxPoint(), other);

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getFxPoint().hashCode();
    }

}
