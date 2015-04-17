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

import info.gianlucacosta.helios.product.ProductInfoService;
import javafx.scene.image.Image;

import java.util.Map;

/**
 * Standardizes common application information for JavaFX applications
 */
public interface AppInfoService extends ProductInfoService {

    Image getMainIcon(int size);

    Map<Integer, Image> getMainIcons();
}