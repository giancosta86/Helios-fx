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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pane containing and aligning buttons.
 * <p>
 * Its associated CSS class is <i>buttonsPane</i>
 * <p>
 * When adding a button to the pane, you can associate it with a <i>button
 * outcome</i>: in this case, when the button is clicked, the
 * <i>buttonOutcome</i> property of the pane will be set to that value, and the
 * dialog will close: it's then up to the dialog to behave according to this
 * outcome.
 */
public class ButtonsPane extends GridPane {

    private final List<Button> buttons;
    private final Stage dialogStage;
    private ButtonOutcome buttonOutcome;

    public ButtonsPane(Stage dialogStage) {
        this.buttons = new ArrayList<>();

        this.dialogStage = dialogStage;

        setPadding(new Insets(15));
        setHgap(20);
        getStyleClass().add("buttonsPane");
    }

    /**
     * Adds a new button
     *
     * @param button the button to add
     */
    public void addButton(Button button) {
        buttons.add(button);
        setupButtons(buttons);
    }

    /**
     * Adds a new button to the pane, and attaches and action handler in order
     * to make it set the button outcome to the given value and close the dialog
     *
     * @param button        the button to add
     * @param buttonOutcome the outcome that must result from clicking that
     *                      button
     */
    public void addButton(Button button, final ButtonOutcome buttonOutcome) {
        button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ButtonsPane.this.buttonOutcome = buttonOutcome;
                dialogStage.hide();
            }

        });

        addButton(button);
    }

    /**
     * Adds a button to the pane, setting it as "default button"
     *
     * @param button the button to add
     */
    public void addDefaultButton(Button button) {
        button.setDefaultButton(true);

        addButton(button);
    }

    /**
     * Adds a button to the pane, sets it as "default button" and also makes it
     * set the given button outcome when clicked
     *
     * @param button        the button to add
     * @param buttonOutcome the expected outcome
     */
    public void addDefaultButton(Button button, final ButtonOutcome buttonOutcome) {
        button.setDefaultButton(true);

        addButton(button, buttonOutcome);
    }

    /**
     * Adds a "cancel" button (in the Java-FX sense of the term) to the pane
     *
     * @param button the button to add
     */
    public void addCancelButton(Button button) {
        button.setCancelButton(true);

        addButton(button);
    }

    /**
     * Adds a "cancel" button (in the Java-FX sense of the term) to the pane,
     * and makes it set the given button outcome
     *
     * @param button        the button to add
     * @param buttonOutcome the expected outcome
     */
    public void addCancelButton(Button button, final ButtonOutcome buttonOutcome) {
        button.setCancelButton(true);

        addButton(button, buttonOutcome);
    }

    /**
     * Called after every call to addButton(), it should reset the child
     * controls of the buttons pane (almost always, just the buttons themselves)
     * and lay them out accordingly
     *
     * @param buttons the collection of buttons
     */
    protected void setupButtons(Collection<Button> buttons) {
        double buttonPercentWidth = 100.0 / buttons.size();

        getChildren().clear();
        getColumnConstraints().clear();

        for (Button button : buttons) {
            add(button, getChildren().size(), 0);

            button.setMaxWidth(Double.MAX_VALUE);

            getColumnConstraints().add(
                    ColumnConstraintsBuilder.create()
                            .percentWidth(buttonPercentWidth)
                            .halignment(HPos.CENTER)
                            .build());
        }
    }

    /**
     * @return the current outcome obtained from pressing a button
     */
    protected ButtonOutcome getButtonOutcome() {
        return buttonOutcome;
    }

    void resetButtonOutcome() {
        buttonOutcome = null;
    }

    /**
     * @return the buttons owned by the buttons pane. They are not necessarily
     * the buttons visually attached to the buttons pane, just the ones added
     * via addButton()
     */
    protected Collection<Button> getButtons() {
        return buttons;
    }

}
