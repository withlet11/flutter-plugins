/*
 * LightPlugin
 *
 * Modified code
 * Copyright 2024 Yasuhiro Yamakawa <withlet11@gmail.com>
 *
 * - Converted Java code to Kotlin code.
 * - Refactored.
 *
 * ************************************************************
 * Original code
 * Copyright 2018 Copenhagen Center for Health Technology (CACHET)
 *   at the Technical University of Denmark (DTU).
 * https://github.com/cph-cachet/flutter-plugins/tree/master/packages/light
 * ************************************************************
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.withlet11.light

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel

/**
 * LightPlugin
 */
class LightPlugin : FlutterPlugin, EventChannel.StreamHandler {
    private var sensorEventListener: SensorEventListener? = null
    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null
    private var eventChannel: EventChannel? = null
    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        /** Init sensor manager */
        val context: Context = flutterPluginBinding.applicationContext
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        /** Init event channel */
        val binaryMessenger: BinaryMessenger = flutterPluginBinding.binaryMessenger
        eventChannel = EventChannel(binaryMessenger, STEP_COUNT_CHANNEL_NAME)
        eventChannel?.setStreamHandler(this)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        /** Cancel the handling of stream data */
        eventChannel?.setStreamHandler(null)
        onCancel(null)
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink) {
        /** Set up the event sensor for the light sensor */
        sensorEventListener = createSensorEventListener(events)
        sensorManager?.registerListener(
            sensorEventListener,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onCancel(arguments: Any?) {
        /** Finish listening to events */
        sensorManager?.unregisterListener(sensorEventListener)
    }

    private fun createSensorEventListener(events: EventChannel.EventSink): SensorEventListener {
        return object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                /** Do nothing */
            }

            override fun onSensorChanged(event: SensorEvent) {
                /** Extract lux value and send it to Flutter via the event sink */
                val lux = event.values[0].toInt()
                events.success(lux)
            }
        }
    }

    companion object {
        private const val STEP_COUNT_CHANNEL_NAME = "light.eventChannel"
    }
}