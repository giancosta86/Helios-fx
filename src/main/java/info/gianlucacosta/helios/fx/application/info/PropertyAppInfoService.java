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

import info.gianlucacosta.helios.product.PropertyProductInfoService;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.*;

/**
 * Extends the features of the related class provided by
 * helios-core, adding methods for JavaFX applications.
 * <p>
 * More precisely, it supports 4 more properties in the property file, one for each main icon:
 * they have the form <i>mainIcon.X</i> where X is a value in the set {16, 32, 64, 128}; if one
 * of such entries is missing, the related icon won't be loaded and getMainIcon() will return
 * null for it.
 */
public class PropertyAppInfoService
        extends PropertyProductInfoService
        implements AppInfoService {

    private final Map<Integer, Image> mainIcons;


    public PropertyAppInfoService(Properties productProperties) {
        super(productProperties);

        mainIcons = loadMainIcons();
    }

    private Map<Integer, Image> loadMainIcons() {
        Map<Integer, Image> result = new HashMap<>();

        for (int mainIconSize : Arrays.asList(16, 32, 64, 128)) {
            String iconPropertyKey = String.format("mainIcon%d", mainIconSize);
            String iconResourcePath = getProductProperty(iconPropertyKey);

            if (iconResourcePath == null) {
                continue;
            }

            if (!iconResourcePath.startsWith("/")) {
                throw new IllegalStateException(
                        String.format(
                                "The icon resource path for '%s' must start with '/'",
                                iconPropertyKey
                        ));
            }

            InputStream mainIconStream = getClass().getResourceAsStream(iconResourcePath);

            if (mainIconStream != null) {
                result.put(mainIconSize, new Image(mainIconStream));
            }
        }

        return result;
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
