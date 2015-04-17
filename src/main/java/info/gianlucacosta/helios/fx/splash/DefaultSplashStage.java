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

package info.gianlucacosta.helios.fx.splash;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Collection;
import java.util.Collections;

/**
 * A basic splash stage, transparent and showing just the provided image and a
 * progress indicator.
 * <p>
 * To use it, instantiate it and call its show() and close() methods.
 * <p>
 * You can also customize this stage by creating a subclass and using the getter
 * methods to retrieve its visual controls and change their settings.
 */
public class DefaultSplashStage extends Stage {

    private final VBox splashBox;
    private final ImageView splashImageView;
    private final ProgressIndicator splashIndicator;

    public DefaultSplashStage(Image splashImage) {
        this(splashImage, Collections.<Image>emptyList());
    }

    public DefaultSplashStage(Image splashImage, Collection<Image> stageIcons) {
        super(StageStyle.TRANSPARENT);

        splashBox = new VBox();
        splashBox.setSpacing(10);
        splashBox.setPadding(new Insets(15));
        splashBox.setAlignment(Pos.CENTER);

        splashImageView = new ImageView(splashImage);
        splashBox.getChildren().add(splashImageView);

        splashIndicator = new ProgressIndicator();
        splashBox.getChildren().add(splashIndicator);

        Scene splashScene = new Scene(splashBox);
        splashScene.setFill(null);

        getIcons().addAll(stageIcons);
        setScene(splashScene);
        sizeToScene();

        centerOnScreen();
    }

    public VBox getSplashBox() {
        return splashBox;
    }

    public ImageView getSplashImageView() {
        return splashImageView;
    }

    public ProgressIndicator getSplashIndicator() {
        return splashIndicator;
    }

}
