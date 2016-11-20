# Helios - FX

*Library of ScalaFX utilities*


## Introduction

**Helios-fx** is one of the modules composing the [Helios](https://www.facebook.com/pages/Helios/206962992779275) project - a multifaceted Scala library.

It is based on [Helios-core](https://github.com/giancosta86/Helios-core) and provides several ScalaFX utilities:

* simplified app startup, showing a ready-made, icon-based splash stage - via **AppBase**, **AppMain** and **SplashStage**

* easy and tidy FXML loading provided by **FxmlScene**, which also supports injecting variable into its controller

* a gallery of output and input dialogs - via **Alerts** and **InputDialogs**, as well as dedicated dialogs - in particular, **BusyDialog**

* **FileChooserExtensions**, to enhance the behaviour of FileChooser dialogs

* a **Workspace** class, handling document lifecycle

* extension methods, provided by **Includes**. To use them, just import them à la ScalaFX:

  ```scala
  import info.gianlucacosta.helios.fx.Includes._
  ```

* **FxEngine**, to easily initialize the FX toolkit in contexts where an app might not be available - for example, *automated tests*

* a ready-made **AboutBox**


Helios is meant to evolve over time - please refer to its Scaladoc or its source code for more details on its current packages.


## Installation

For further information about downloading or referencing the library via Gradle or Maven, please visit [its page](https://bintray.com/giancosta86/Hephaestus/Helios-fx) on Hephaestus, my Gradle/Maven repository.

Helios-fx is now also an [OSGi](https://www.osgi.org/) bundle.


## Further references

* [Facebook page](https://www.facebook.com/Helios-206962992779275/)
