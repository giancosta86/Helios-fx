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

package info.gianlucacosta.helios.fx.dialogs.buttonspane;

import info.gianlucacosta.helios.collections.general.CollectionItems;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.stage.Stage;

import java.util.Collection;

/**
 * Buttons pane just containing a standard "OK" button, having a fixed width
 */
public class OkButtonsPane extends CommonButtonsPane {

    public OkButtonsPane(Stage dialogStage) {
        super(dialogStage);

        addOkButton();
    }

    @Override
    protected void setupButtons(Collection<Button> buttons) {
        Button okButton = CollectionItems.getSingle(buttons);

        okButton.setPrefWidth(80);

        getColumnConstraints().clear();
        getColumnConstraints().addAll(
                ColumnConstraintsBuilder.create()
                        .halignment(HPos.CENTER)
                        .percentWidth(100)
                        .build());

        add(okButton, 0, 0);
    }

}
