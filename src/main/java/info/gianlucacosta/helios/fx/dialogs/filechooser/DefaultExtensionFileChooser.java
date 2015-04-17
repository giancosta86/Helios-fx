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

package info.gianlucacosta.helios.fx.dialogs.filechooser;

import info.gianlucacosta.helios.application.io.CommonInputService;
import info.gianlucacosta.helios.application.io.CommonQuestionOutcome;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import java.io.File;
import java.util.List;

/**
 * A decorator for FileChooser, providing a default extension if the user does
 * not. It also opens by default into the directory of the latest opened/saved
 * file.
 * <p>
 * This class does not extend FileChooser because that is final...
 */
public class DefaultExtensionFileChooser {

    private final FileChooser fileChooser;
    private final CommonInputService commonInputService;

    private String defaultExtension;

    public DefaultExtensionFileChooser(CommonInputService commonInputService, String defaultExtension) {
        this(new FileChooser(), commonInputService, defaultExtension);
    }

    public DefaultExtensionFileChooser(FileChooser fileChooser, CommonInputService commonInputService, String defaultExtension) {
        this.fileChooser = fileChooser;
        this.commonInputService = commonInputService;
        this.defaultExtension = defaultExtension;
    }

    public String getDefaultExtension() {
        return defaultExtension;
    }

    public void setDefaultExtension(String defaultExtension) {
        this.defaultExtension = defaultExtension;
    }

    public void setTitle(String string) {
        fileChooser.setTitle(string);
    }

    public String getTitle() {
        return fileChooser.getTitle();
    }

    public StringProperty titleProperty() {
        return fileChooser.titleProperty();
    }

    public void setInitialDirectory(File file) {
        fileChooser.setInitialDirectory(file);
    }

    public File getInitialDirectory() {
        return fileChooser.getInitialDirectory();
    }

    public ObjectProperty<File> initialDirectoryProperty() {
        return fileChooser.initialDirectoryProperty();
    }

    public ObservableList<ExtensionFilter> getExtensionFilters() {
        return fileChooser.getExtensionFilters();
    }

    public File showOpenDialog(Window window) {
        File result = fileChooser.showOpenDialog(window);

        if (result != null) {
            setInitialDirectory(result.getParentFile());
        }

        return result;
    }

    public List<File> showOpenMultipleDialog(Window window) {
        return fileChooser.showOpenMultipleDialog(window);
    }

    /**
     * Asks the user to save a file, adding the default extension to the file
     * name if none was provided; in this case, however, if the file already
     * exists, then the dialog is shown again
     *
     * @param window the parent window
     * @return the chosen file path
     */
    public File showSaveDialog(Window window) {
        while (true) {
            File result = fileChooser.showSaveDialog(window);

            if (result == null) {
                return null;
            }

            setInitialDirectory(result.getParentFile());

            if (!result.getName().endsWith(defaultExtension)) {
                result = new File(String.format("%s%s", result.getAbsolutePath(), defaultExtension));

                if (result.exists()) {
                    if (commonInputService.askYesNoQuestion(
                            String.format("The file '%s' already exists!\nDo you really want to overwrite it?", result.getName())
                    ) != CommonQuestionOutcome.YES) {
                        continue;
                    }
                }
            }

            return result;
        }
    }

    /**
     * @return the FileChooser object internally used by this class
     */
    protected FileChooser getFileChooser() {
        return fileChooser;
    }
}
