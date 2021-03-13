package com.pdm.minhaprova

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.pdm.minhaprova.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button1.setOnClickListener {

            val intent = Intent(this, ActivityAcao1::class.java)
            startActivityForResult(intent, 1)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val textoDigitado = data?.getStringExtra("textoDigitado").toString()

            binding.text1.text = textoDigitado

        }else if (resultCode == Activity.RESULT_CANCELED){
            val snackbar =
                Snackbar.make(binding.root, "Cancelado", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

    }
}