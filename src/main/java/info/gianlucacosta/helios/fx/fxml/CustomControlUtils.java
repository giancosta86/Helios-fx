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

package info.gianlucacosta.helios.fx.fxml;

import info.gianlucacosta.helios.annotations.Experimental;
import javafx.fxml.FXMLLoader;

/**
 * Static class containing utilities for creating a custom control via FXML.
 */
@Experimental
public final class CustomControlUtils {

    private CustomControlUtils() {
    }

    public static void setRootAndController(Object customControl) {
        setRootAndController(customControl, new FXMLLoader());
    }

    public static void setRootAndController(Object customControl, FXMLLoader fxmlLoader) {
        fxmlLoader.setRoot(customControl);
        fxmlLoader.setController(customControl);

        Class<?> customControlClass = customControl.getClass();
        FXMLLoaderUtils.loadClassRelatedFxml(fxmlLoader, customControlClass);
    }

}
