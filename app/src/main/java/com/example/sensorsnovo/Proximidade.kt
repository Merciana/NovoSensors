package com.example.sensorsnovo

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class Proximidade : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var lolo:Sensor? = null
    private lateinit var proximo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximidade)
        proximo = findViewById(R.id.proximity)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        ProximidadeFormula()
    }

    private fun ProximidadeFormula(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lolo = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_PROXIMITY){
            val color = if(event.values[0] < Sensor.TYPE_PROXIMITY) Color.BLUE else Color.RED
            proximo.setBackgroundColor(color)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lolo, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}