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

import info.gianlucacosta.helios.exceptions.ValidationException;
import info.gianlucacosta.helios.fx.dialogs.buttonspane.OkButtonsPane;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.EnumSet;

/**
 * A NodeDialog just showing a WebView and, by default, an "OK" button
 */
public class HtmlDialog extends NodeDialog<Void> {

    private final WebView webView = new WebView();

    public HtmlDialog(String title) {
        super(title);
    }

    public HtmlDialog(String title, EnumSet<DialogOptions> options, Collection<? extends Image> stageIcons) {
        super(title, options, stageIcons);
    }

    @Override
    protected Node createContentNode() {
        return webView;
    }

    @Override
    protected ButtonsPane createButtonsPane(Stage dialogStage) {
        return new OkButtonsPane(dialogStage);
    }

    /**
     * @return the underlying web engine
     */
    public WebEngine getWebEngine() {
        return webView.getEngine();
    }

    @Override
    protected boolean shouldRetrieveValue() {
        return false;
    }

    @Override
    protected Void retrieveValue() throws ValidationException {
        return null;
    }
}
