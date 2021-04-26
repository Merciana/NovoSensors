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

class Acelerometro : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var lele: TextView

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acelerometro)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        lele = findViewById(R.id.ProximidadeResultado)

        ProximoFormula()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT) //Conferir se dá certo
    private fun ProximoFormula(){
       sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager. getDefaultSensor(Sensor.TYPE_ACCELEROMETER). also{
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_FASTEST)

        }

    }

    override fun onSensorChanged(A: SensorEvent?) {
        if(A?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val sides= A.values[0]
            val upDown = A.values[1]

            lele.apply {
                rotationX = upDown * 3f
                rotationY = sides * 3f
                rotation = -sides
                translationX = sides * -10
                translationY = upDown * 10
            }
            val color = if(upDown.toInt() == 0 && sides.toInt() == 0) Color.GREEN else Color.RED
            lele.setBackgroundColor(color)

            lele.text = "up/down ${upDown.toInt()}\n left/right ${sides.toInt()}"
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