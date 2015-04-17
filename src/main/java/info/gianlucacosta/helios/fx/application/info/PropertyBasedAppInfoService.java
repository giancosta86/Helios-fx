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

package info.gianlucacosta.helios.fx.application.info;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Extends the features of the PropertyBasedAppInfoService class provided by
 * helios-core, adding methods for JavaFX applications.
 * <p>
 * This implementation loads 16x16, 32x32, 64x64 and 128x128 "main" icons of the
 * application, representing the application itself: they must be named
 * <i>icons/mainIconX.png</i> where X is the specific size (16, 32, 64 or 128);
 * if any of these icons is missing, it won't be loaded and, when requested by a
 * client of this class, it will be returned as null.
 *
 * @deprecated Use PropertyAppInfoService instead
 */
@Deprecated
public class PropertyBasedAppInfoService
        extends info.gianlucacosta.helios.application.PropertyBasedAppInfoService
        implements AppInfoService {

    private final Map<Integer, Image> mainIcons;

    public PropertyBasedAppInfoService(String referenceClassName) throws ClassNotFoundException {
        this(Class.forName(referenceClassName));
    }

    public PropertyBasedAppInfoService(Class<?> referenceClass) {
        super(referenceClass);
        mainIcons = new HashMap<>();

        loadMainIcons(referenceClass);
    }

    private void loadMainIcons(Class<?> referenceClass) {
        for (int mainIconSize : Arrays.asList(16, 32, 64, 128)) {
            InputStream mainIconStream = referenceClass.getResourceAsStream(String.format(getAppProperty("app.mainIconPath"), mainIconSize));

            if (mainIconStream != null) {
                mainIcons.put(mainIconSize, new Image(mainIconStream));
            }
        }
    }

    @Override
    public Map<Integer, Image> getMainIcons() {
        return Collections.unmodifiableMap(mainIcons);
    }

    @Override
    public Image getMainIcon(int size) {
        return mainIcons.get(size);
    }
}
