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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Static class containing utilities for loading FXML files
 */
public final class FXMLLoaderUtils {

    private FXMLLoaderUtils() {
    }

    /**
     * Uses the given FXML loader to retrieve and load an FXML resource file
     * residing in the same package as the target class and having its same
     * filename, of course with <i>.fxml</i> extension
     *
     * @param fxmlLoader  The loader
     * @param targetClass The target class
     * @return the root object returned by the FXML loader after parsing the
     * file
     */
    public static Parent loadClassRelatedFxml(FXMLLoader fxmlLoader, Class<?> targetClass) {
        String fxmlFileName = String.format("%s.fxml", targetClass.getSimpleName());
        try {
            return (Parent) fxmlLoader.load(targetClass.getResourceAsStream(fxmlFileName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
