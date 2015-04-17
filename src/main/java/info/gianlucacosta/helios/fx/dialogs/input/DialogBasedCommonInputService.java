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

package info.gianlucacosta.helios.fx.dialogs.input;

import info.gianlucacosta.helios.application.io.CommonInputService;
import info.gianlucacosta.helios.application.io.CommonOutputService;
import info.gianlucacosta.helios.application.io.CommonQuestionOutcome;
import info.gianlucacosta.helios.beans.events.ValueNotificationListener;
import info.gianlucacosta.helios.collections.general.CollectionItems;
import info.gianlucacosta.helios.conditions.Condition;
import info.gianlucacosta.helios.conditions.SatisfiedCondition;
import info.gianlucacosta.helios.exceptions.ValidationException;
import info.gianlucacosta.helios.fx.application.info.AppInfoService;

import java.util.Collection;

/**
 * Implementation of CommonInputService employing common input dialogs
 */
public class DialogBasedCommonInputService implements CommonInputService {

    private final AppInfoService appInfoService;
    private final CommonOutputService commonOutputService;

    public DialogBasedCommonInputService(AppInfoService appInfoService, CommonOutputService commonOutputService) {
        this.appInfoService = appInfoService;
        this.commonOutputService = commonOutputService;
    }

    @Override
    public String askForString(String prompt, String initialValue) {
        return askForString(prompt, initialValue, new SatisfiedCondition<String>());
    }

    @Override
    public String askForString(String prompt, String initialValue, Condition<String> valueCondition) {
        StringInputDialog stringInputDialog = new StringInputDialog(appInfoService.getTitle(), prompt, initialValue, true, valueCondition);

        stringInputDialog.addValidationFailedListener(new ValueNotificationListener<ValidationException>() {
            @Override
            public void onNotify(ValidationException validationException) {
                commonOutputService.showWarning(validationException.getMessage());
            }

        });

        return stringInputDialog.show();
    }

    @Override
    public Integer askForInteger(String prompt, Integer initialValue) {
        return askForInteger(prompt, initialValue, new SatisfiedCondition<Integer>());
    }

    @Override
    public Integer askForInteger(String prompt, Integer initialValue, Condition<Integer> valueCondition) {
        IntegerInputDialog integerInputDialog = new IntegerInputDialog(appInfoService.getTitle(), prompt, initialValue, valueCondition);

        integerInputDialog.addValidationFailedListener(new ValueNotificationListener<ValidationException>() {
            @Override
            public void onNotify(ValidationException validationException) {
                commonOutputService.showWarning(validationException.getMessage());
            }

        });

        return integerInputDialog.show();
    }

    @Override
    public Double askForDouble(String prompt, Double initialValue) {
        return askForDouble(prompt, initialValue, new SatisfiedCondition<Double>());
    }

    @Override
    public Double askForDouble(String prompt, Double initialValue, Condition<Double> valueCondition) {
        DoubleInputDialog doubleInputDialog = new DoubleInputDialog(appInfoService.getTitle(), prompt, initialValue, valueCondition);

        doubleInputDialog.addValidationFailedListener(new ValueNotificationListener<ValidationException>() {
            @Override
            public void onNotify(ValidationException validationException) {
                commonOutputService.showWarning(validationException.getMessage());
            }

        });

        return doubleInputDialog.show();
    }

    @Override
    public <TValue> TValue askForItem(String prompt, Collection<? extends TValue> sourceValues) {
        return askForItem(prompt, sourceValues, null, new SatisfiedCondition<TValue>());
    }

    @Override
    public <TValue> TValue askForItem(String prompt, Collection<? extends TValue> sourceValues, TValue initialValue) {
        return askForItem(prompt, sourceValues, initialValue, new SatisfiedCondition<TValue>());
    }

    @Override
    public <TValue> TValue askForItem(String prompt, Collection<? extends TValue> sourceValues, TValue initialValue, Condition<TValue> valueCondition) {
        if (initialValue == null) {
            initialValue = CollectionItems.getFirstOrDefault(sourceValues);
        }

        ComboBoxInputDialog<TValue> selectionInputDialog = new ComboBoxInputDialog<>(appInfoService.getTitle(), prompt, sourceValues, initialValue, valueCondition);

        selectionInputDialog.addValidationFailedListener(new ValueNotificationListener<ValidationException>() {
            @Override
            public void onNotify(ValidationException validationException) {
                commonOutputService.showWarning(validationException.getMessage());
            }

        });

        return selectionInputDialog.show();
    }

    @Override
    public CommonQuestionOutcome askYesNoQuestion(String title, String message) {
        YesNoQuestionDialog questionDialog = new YesNoQuestionDialog(title, message);

        return questionDialog.show();
    }

    @Override
    public CommonQuestionOutcome askYesNoQuestion(String message) {
        return askYesNoQuestion(appInfoService.getTitle(), message);
    }

    @Override
    public CommonQuestionOutcome askYesNoCancelQuestion(String title, String message) {
        YesNoCancelQuestionDialog questionDialog = new YesNoCancelQuestionDialog(title, message);

        return questionDialog.show();
    }

    @Override
    public CommonQuestionOutcome askYesNoCancelQuestion(String message) {
        return askYesNoCancelQuestion(appInfoService.getTitle(), message);
    }

}
