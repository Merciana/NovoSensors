package com.example.sensorsnovo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.sensorsnovo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
   private lateinit var binding: ActivityMainBinding

   val S_BUTAO1 = 50
    val S_BUTAO2 = 60
    val S_BUTAO3 = 80

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button.setOnClickListener{
            val i = Intent(this, Proximidade::class.java)
            startActivityForResult(i, S_BUTAO1)
        }
        binding.button2.setOnClickListener {
            val i = Intent(this, Luminosidade::class.java)
            startActivityForResult(i, S_BUTAO2)
        }
        binding.button3.setOnClickListener {
            val i = Intent(this, Acelerometro::class.java)
            startActivityForResult(i, S_BUTAO3)
        }
        binding.button4.setOnClickListener {
            val i = Intent(this, Giroscopio::class.java)
            startActivity(i)
        }
    }

    override fun onStop() {
        Log.i("TESTE", "onStop() invocado.")
        super.onStop()
    }
}
