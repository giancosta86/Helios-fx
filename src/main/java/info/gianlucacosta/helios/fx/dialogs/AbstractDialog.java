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

import info.gianlucacosta.helios.beans.events.ValueNotificationEvent;
import info.gianlucacosta.helios.beans.events.ValueNotificationListener;
import info.gianlucacosta.helios.exceptions.ValidationException;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

/**
 * Basic implementation of Dialog
 */
public abstract class AbstractDialog<TValue> implements Dialog<TValue> {

    private final String title;
    private final EnumSet<DialogOptions> options;
    private final Collection<? extends Image> stageIcons;
    private final ValueNotificationEvent<ValidationException> validationFailedEvent;
    private Stage stage;

    public AbstractDialog(String title) {
        this(title, EnumSet.noneOf(DialogOptions.class), Collections.<Image>emptyList());
    }

    public AbstractDialog(String title, EnumSet<DialogOptions> options, Collection<? extends Image> stageIcons) {
        this.title = title;
        this.options = EnumSet.copyOf(options);
        this.stageIcons = stageIcons;

        validationFailedEvent = new ValueNotificationEvent<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public EnumSet<DialogOptions> getOptions() {
        return EnumSet.copyOf(options);
    }

    protected Collection<? extends Image> getStageIcons() {
        return Collections.unmodifiableCollection(stageIcons);
    }

    /**
     * Returns the dialog's stage, creating it on the first access by calling
     * createStage()
     *
     * @return the Stage used by the dialog
     */
    protected final Stage getStage() {
        if (stage == null) {
            stage = createStage();
        }

        return stage;
    }

    /**
     * Creates the stage for the dialog.
     * <p>
     * It is called just once per dialog instance
     *
     * @return the dialog stage
     */
    protected Stage createStage() {
        Stage result = new Stage();

        result.setTitle(title);
        result.setResizable(options.contains(DialogOptions.RESIZABLE));

        if (!stageIcons.isEmpty()) {
            result.getIcons().addAll(stageIcons);
        } else {
            result.initStyle(StageStyle.UTILITY);
        }

        result.initModality(Modality.APPLICATION_MODAL);

        return result;
    }

    /**
     * Called by the show() method before the first time the stage is shown by
     * that method call - in other words, if the stage is shown multiple times
     * during that single call to show() (for example, because of validation),
     * onDialogShown() is called just once
     */
    protected void onDialogShown() {
    }

    /**
     * Called by the show() method every single time the stage is shown by that
     * method call - in other words, if the stage is shown multiple times during
     * that single call to show() (for example, because of validation),
     * onStageShown() is called every time
     */
    protected void onStageShown() {
    }

    @Override
    public TValue show() {
        Stage stageToShow = getStage();

        onDialogShown();

        while (true) {
            onStageShown();

            stageToShow.showAndWait();

            if (shouldRetrieveValue()) {
                try {
                    return retrieveValue();
                } catch (ValidationException ex) {
                    validationFailedEvent.fire(ex);
                    continue;
                }
            }

            break;
        }

        return getDefaultValue();
    }

    /**
     * This method is called just after the dialog has been hidden (by the user
     * or programmatically) and should tell whether to retrieve the dialog's
     * value.
     *
     * @return true if show() should call retrieveValue() in order to return a
     * value, or false if show() should return the defaultValue (obtained from
     * getDefaultValue())
     */
    protected abstract boolean shouldRetrieveValue();

    /**
     * This method is only called if shouldRetrieveValue() returns true
     *
     * @return the dialog value, as returned by show()
     * @throws ValidationException exception to throw whenever it is impossible
     *                             to generate the dialog's value. In this case, all the "validationFailed"
     *                             listeners are executed, and the dialog doesn't get closed.
     */
    protected abstract TValue retrieveValue() throws ValidationException;

    /**
     * @return the "default value" of the dialog - the value returned by show()
     * whenever the user chooses to close the dialog in a way for which value
     * retrieval is not provided (for example, closing its stage using the
     * "Close" button in the stage decoration)
     */
    protected TValue getDefaultValue() {
        return null;
    }

    /**
     * Adds a listener that is called whenever a ValidationException is thrown
     * by retrieveValue()
     *
     * @param listener the listener to add
     */
    public void addValidationFailedListener(ValueNotificationListener<ValidationException> listener) {
        validationFailedEvent.addListener(listener);
    }

    /**
     * Removes a listener related to ValidationException
     *
     * @param listener the listener to remove
     */
    public void removeValidationFailedListener(ValueNotificationListener<ValidationException> listener) {
        validationFailedEvent.removeListener(listener);
    }

}
