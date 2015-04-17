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

package info.gianlucacosta.helios.fx.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Collection;
import java.util.EnumSet;

/**
 * A dialog showing both an icon and a "main" node in addition to the buttons
 * pane
 */
public abstract class IconDialog<TValue> extends NodeDialog<TValue> {

    private final Image iconImage;

    public IconDialog(String title, Image iconImage) {
        super(title);
        this.iconImage = iconImage;
    }

    public IconDialog(String title, EnumSet<DialogOptions> options, Collection<? extends Image> stageIcons, Image iconImage) {
        super(title, options, stageIcons);
        this.iconImage = iconImage;
    }

    @Override
    protected HBox createContentNode() {
        HBox contentBox = new HBox();
        contentBox.setPadding(new Insets(10));
        contentBox.setSpacing(5);
        contentBox.setAlignment(Pos.CENTER_LEFT);

        ImageView iconView = new ImageView(iconImage);
        contentBox.getChildren().add(iconView);

        Node mainNode = createMainNode();
        contentBox.getChildren().add(mainNode);

        return contentBox;
    }

    protected abstract Node createMainNode();

}
