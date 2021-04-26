package com.example.sensorsnovo

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate

class Giroscopio : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var giroscope : TextView
    private var lulu:Sensor? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giroscopio)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        giroscope = findViewById(R.id.giroscope)

        giroscopeFormula()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun giroscopeFormula(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager. getDefaultSensor(Sensor.TYPE_GYROSCOPE). also{
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_FASTEST)
        }

    }

    override fun onSensorChanged(G: SensorEvent?) {
        if(G?.sensor?.type == Sensor.TYPE_GYROSCOPE){
            if (G.values[2] > 0.5f){
                giroscope.setBackgroundColor(Color.BLUE)
            }else if(G.values[2] < -0.5f){
                giroscope.setBackgroundColor(Color.RED)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }
}

