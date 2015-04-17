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

package info.gianlucacosta.helios.fx.geometry;

import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * Point, <em>in the current JavaFX scene</em>, whose coordinates in the
 * constructor are expressed in relation to a given node, which acts as the
 * reference system instead of the scene
 */
public class RelativeScenePoint extends Point2D {

    /**
     * @param x                   the x coordinate of the point <em>in the scene</em>
     * @param y                   the y coordinate of the point <em>in the scene</em>
     * @param referenceSystemNode the reference system node, which becomes the
     *                            reference system for this point
     */
    public RelativeScenePoint(double x, double y, Node referenceSystemNode) {
        super(
                x - referenceSystemNode.localToScene(0, 0).getX(),
                y - referenceSystemNode.localToScene(0, 0).getY());
    }

}
