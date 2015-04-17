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

package info.gianlucacosta.helios.fx.dialogs.message;

import info.gianlucacosta.helios.fx.dialogs.ButtonsPane;
import info.gianlucacosta.helios.fx.dialogs.IconDialog;
import info.gianlucacosta.helios.fx.dialogs.buttonspane.OkButtonsPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Basic implementation of a message dialog
 * <p>
 * By default, it does not retrieve input from the user: it just shows a message
 * and can be closed via an "OK" button
 */
public abstract class MessageDialog extends IconDialog<Void> {

    private final String message;

    public MessageDialog(String title, Image iconImage, String message) {
        super(title, iconImage);

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    protected Node createMainNode() {
        return new Label(message);
    }

    @Override
    protected boolean shouldRetrieveValue() {
        return false;
    }

    @Override
    protected Void retrieveValue() {
        return null;
    }

    @Override
    protected HBox createContentNode() {
        HBox result = super.createContentNode();

        result.setPadding(new Insets(10, 10, 0, 10));

        return result;
    }

    @Override
    protected ButtonsPane createButtonsPane(Stage stage) {
        OkButtonsPane buttonsPane = new OkButtonsPane(stage);

        buttonsPane.setPadding(new Insets(10, 10, 15, 10));

        return buttonsPane;
    }

}
