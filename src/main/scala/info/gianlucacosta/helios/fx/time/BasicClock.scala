/*^
  ===========================================================================
  Helios - FX
  ===========================================================================
  Copyright (C) 2013-2016 Gianluca Costa
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

package info.gianlucacosta.helios.fx.time

import java.time.Duration
import javafx.beans.property.SimpleIntegerProperty

import scala.annotation.tailrec
import scalafx.Includes._
import scalafx.application.Platform
import scalafx.beans.property.ReadOnlyIntegerProperty

/**
  * Basic clock exposing an FX <i>ticks</i> property,
  * which is increased at each tick by a daemon thread.
  *
  * The timer must be started via <i>start()</i> and
  * should then be stopped via <i>stop()</i> when it is no
  * more needed.
  *
  * To reset the ticks to 0, one should call the reset() button.
  *
  * @param interval The interval between ticks
  */
class BasicClock(interval: Duration) {

  /**
    * The internal thread, updating the FX property
    */
  private class TickThread extends Thread(
    new Runnable {
      @tailrec
      override def run(): Unit = {
        if (!stopped) {
          Thread.sleep(intervalInMillis)

          Platform.runLater {
            _ticks() += 1
          }

          run()
        }
      }
    }
  ) {
    setDaemon(true)
  }


  private val intervalInMillis =
    interval.toMillis

  require(
    intervalInMillis > 0,
    "The interval must be > 0"
  )


  private val _ticks =
    new SimpleIntegerProperty(0)

  def ticks: ReadOnlyIntegerProperty =
    _ticks


  private var started =
    false

  @volatile
  private var stopped =
    false


  /**
    * Starts the clock after creation.
    * After the first call, its does nothing.
    */
  def start(): Unit = {
    if (!started) {
      started =
        true

      new TickThread {
        start()
      }
    }
  }


  /**
    * Stops the clock, if it was started and not stopped;
    * otherwise, it does just nothing.
    */
  def stop(): Unit = {
    stopped =
      true
  }


  /**
    * Resets the ticks without stopping the clock.
    *
    * <i>Must be called from the FX thread.</i>
    */
  def reset(): Unit = {
    _ticks() = 0
  }
}

