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
import info.gianlucacosta.helios.fx.dialogs.buttonspane.CommonButtonsPane;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * An error dialog showing the details of a Throwable instance
 */
public class ThrowableDialog extends ErrorDialog {

    private final Throwable throwable;
    private final VBox mainBox;
    private final TextArea detailsArea;
    private Node baseMainNode;

    public ThrowableDialog(String title, String message, Throwable throwable) {
        super(title, message);

        this.throwable = throwable;

        mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER_LEFT);
        mainBox.setSpacing(10);

        detailsArea = new TextArea();
        detailsArea.setMinSize(800, 500);
        detailsArea.setMaxSize(800, 500);
        detailsArea.setEditable(false);
        detailsArea.setWrapText(false);
    }

    @Override
    protected Node createMainNode() {
        baseMainNode = super.createMainNode();

        detailsArea.appendText(String.format("%s(\"%s\")", throwable.getClass().getName(), throwable.getMessage()));
        detailsArea.appendText("\n");
        detailsArea.appendText("\n");
        detailsArea.appendText("\n");
        detailsArea.appendText("<-- STACK TRACE -->");
        detailsArea.appendText("\n");
        detailsArea.appendText("\n");
        for (StackTraceElement traceElement : throwable.getStackTrace()) {
            detailsArea.appendText(String.format("%s\n", traceElement.toString()));
        }

        mainBox.getChildren().add(baseMainNode);
        return mainBox;
    }

    @Override
    protected ButtonsPane createButtonsPane(final Stage stage) {
        CommonButtonsPane buttonsPane = new CommonButtonsPane(stage);

        buttonsPane.addOkButton();

        final Button showDetailsButton = new Button();

        showDetailsButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                event.consume();

                if (mainBox.getChildren().contains(detailsArea)) {
                    mainBox.getChildren().remove(detailsArea);
                } else {
                    mainBox.getChildren().add(detailsArea);
                }

                getStage().sizeToScene();
                stage.centerOnScreen();
            }

        });

        buttonsPane.addButton(showDetailsButton);

        Button copyDetailsButton = new Button("Copy details");

        copyDetailsButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                event.consume();

                Map<DataFormat, Object> clipboardMap = new HashMap<>();
                clipboardMap.put(DataFormat.PLAIN_TEXT, detailsArea.getText());

                Clipboard.getSystemClipboard().setContent(clipboardMap);
            }

        });

        buttonsPane.addButton(copyDetailsButton);

        ListChangeListener<Node> mainBoxListener = new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> change) {
                if (mainBox.getChildren().contains(detailsArea)) {
                    showDetailsButton.setText("Hide details");
                } else {
                    showDetailsButton.setText("Show details");
                }
            }

        };

        mainBox.getChildren().addListener(mainBoxListener);

        mainBoxListener.onChanged(null);

        return buttonsPane;
    }

}
