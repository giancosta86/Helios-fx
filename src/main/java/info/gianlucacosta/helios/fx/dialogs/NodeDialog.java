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

import info.gianlucacosta.helios.fx.dialogs.buttonspane.ButtonOutcome;
import info.gianlucacosta.helios.fx.dialogs.buttonspane.CommonButtonOutcome;
import info.gianlucacosta.helios.fx.dialogs.buttonspane.OkCancelButtonsPane;
import info.gianlucacosta.helios.fx.style.CssLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.EnumSet;

/**
 * General dialog based on the concepts of <i>content node</i> and
 * <i>buttons pane</i>.
 * <p>
 * More precisely:
 * <ul> <li> the <i>content node</i> occupies most of the dialog, and contains
 * input controls required to interact with the user </li>
 * <li> the <i>buttons pane</i> usually lies at the bottom of the dialog, and
 * contains buttons providing different actions and, often, capable of closing
 * the dialog with a certain <i>button outcome</i> - which affects whether the
 * dialog should return a significant value, as well as the returned value
 * itself </li>
 * </ul>
 * <p>
 * Another important feature of NodeDialog is that it automatically loads and
 * apply a CSS resource, if present, located in the same package as any concrete
 * class inheriting from NodeDialog and having its same name (with
 * <i>.css</i> extension, of course)
 */
public abstract class NodeDialog<TValue> extends AbstractDialog<TValue> {

    private ButtonsPane buttonsPane;

    public NodeDialog(String title) {
        super(title);
    }

    public NodeDialog(String title, EnumSet<DialogOptions> options, Collection<? extends Image> stageIcons) {
        super(title, options, stageIcons);
    }

    /**
     * @return the node that will occupy the main area of this dialog. It is
     * also given the "contentNode" CSS class
     */
    protected abstract Node createContentNode();

    /**
     * @param stage the stage
     * @return the buttons pane used by the dialog. By default, it creates a
     * pane with just standard "OK" and "Cancel" buttons. If null is returned,
     * then no buttons pane will be added to the dialog
     */
    protected ButtonsPane createButtonsPane(Stage stage) {
        return new OkCancelButtonsPane(stage);
    }

    /**
     * Reads the button outcome provided by the buttons pane
     *
     * @return the button outcome provided by the buttons pane, or null if the
     * buttons pane is missing
     */
    protected ButtonOutcome getButtonOutcome() {
        if (buttonsPane == null) {
            return null;
        }

        return buttonsPane.getButtonOutcome();
    }

    @Override
    protected Stage createStage() {
        Stage stage = super.createStage();

        BorderPane dialogPane = new BorderPane();

        Node contentNode = createContentNode();
        contentNode.getStyleClass().add("contentNode");
        dialogPane.setCenter(contentNode);

        buttonsPane = createButtonsPane(stage);

        if (buttonsPane != null) {
            layoutButtonsPane(dialogPane);
        }

        Scene scene = createScene(dialogPane);

        stage.setScene(scene);
        stage.sizeToScene();

        return stage;
    }

    /**
     * Enables you to choose where the buttons pane should stay.
     * <p>
     * By default, the buttons pane is added to the bottom of the dialog.
     *
     * @param dialogPane the dialog pane
     */
    protected void layoutButtonsPane(BorderPane dialogPane) {
        dialogPane.setBottom(buttonsPane);
    }

    /**
     * Creates the scene, and automatically tries to load a CSS having the same
     * basename and path as the concrete class extending this class
     *
     * @param dialogPane the pane containing the content node and the buttons
     *                   pane
     * @return the scene to add to the stage
     */
    protected Scene createScene(BorderPane dialogPane) {
        Scene scene = new Scene(dialogPane);
        new CssLoader(scene).addCssForClass(getClass());
        return scene;
    }

    @Override
    protected void onStageShown() {
        super.onStageShown();

        if (buttonsPane != null) {
            buttonsPane.resetButtonOutcome();
        }
    }

    /**
     * @return by default, <i>true</i> if and only if the button outcome is OK
     */
    @Override
    protected boolean shouldRetrieveValue() {
        return getButtonOutcome() == CommonButtonOutcome.OK;
    }

}
