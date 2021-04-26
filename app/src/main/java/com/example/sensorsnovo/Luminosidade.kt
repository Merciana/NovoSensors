package com.example.sensorsnovo

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class Luminosidade : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var tata:Sensor? = null
    private lateinit var luz: TextView
    private lateinit var Circulo: CircularProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luminosidade)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        luz = findViewById(R.id.LuzView)
        Circulo = findViewById(R.id.CircularLuz)

        LuzFormula()
    }

    private fun LuzFormula(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        tata = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    private fun tata(tata: Float): String{
        return when (tata.toInt()){
            0 -> "Sinal Negro"
            in 1..10 -> "Sinal Sombrio"
            in 11..50 -> "Sinal Cinza"
            in 51..5000 -> "Normal"
            in 5001..25000 -> " Super Brilhante"
            else -> "Cuidado com os olhos!"
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_LIGHT){
            val ligth = event.values[0]

            luz.text = "Sensor: $ligth\n ${tata(ligth)}"
            Circulo.setProgressWithAnimation(ligth)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, tata, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}