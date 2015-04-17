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

package info.gianlucacosta.helios.fx.style;

import javafx.scene.Scene;

import java.net.URL;

/**
 * Utility class that applies CSS stylesheets to a target scene
 */
public class CssLoader {

    private final Scene targetScene;

    /**
     * Creates this class and makes it target the given scene
     *
     * @param targetScene the scene to which CSS stylesheets will be applied
     */
    public CssLoader(Scene targetScene) {
        this.targetScene = targetScene;
    }

    /**
     * Attaches a CSS resource file located in the same package as the reference
     * class and having its same filename, of course with the .css
     * extension.
     *
     * @param referenceClass the reference class
     * @return true if the stylesheet was found and loaded, false otherwise
     */
    public boolean addCssForClass(Class<?> referenceClass) {
        URL cssResource = referenceClass.getResource(String.format("%s.css", referenceClass.getSimpleName()));

        if (cssResource == null) {
            return false;
        }

        targetScene.getStylesheets().add(cssResource.toExternalForm());

        return true;
    }

}
