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

import info.gianlucacosta.helios.application.io.CommonOutputService;
import info.gianlucacosta.helios.fx.application.info.AppInfoService;

/**
 * Implementation of CommonOutputService employing common message dialogs
 */
public class DialogBasedOutputService implements CommonOutputService {

    private final AppInfoService appInfoService;

    public DialogBasedOutputService(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @Override
    public void showInfo(String title, String message) {
        InfoDialog infoDialog = new InfoDialog(title, message);

        infoDialog.show();
    }

    @Override
    public void showInfo(String message) {
        showInfo(appInfoService.getTitle(), message);
    }

    @Override
    public void showWarning(String title, String message) {
        WarningDialog warningDialog = new WarningDialog(title, message);

        warningDialog.show();
    }

    @Override
    public void showWarning(String message) {
        showWarning(appInfoService.getTitle(), message);
    }

    @Override
    public void showError(String title, String message) {
        ErrorDialog errorDialog = new ErrorDialog(title, message);

        errorDialog.show();
    }

    @Override
    public void showError(String message) {
        showError(appInfoService.getTitle(), message);
    }

    @Override
    public void showThrowable(String title, String message, Throwable throwable) {
        ThrowableDialog throwableDialog = new ThrowableDialog(title, message, throwable);

        throwableDialog.show();
    }

    @Override
    public void showThrowable(String message, Throwable throwable) {
        showThrowable(appInfoService.getTitle(), message, throwable);
    }

}
