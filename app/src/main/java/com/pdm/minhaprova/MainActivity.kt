package com.pdm.minhaprova

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
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

        mensagemBemvindo()

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("Salvando os dados")
        println(binding.text1.text.toString())
        outState.putString("textView1", binding.text1.text.toString())
        outState.putString("textView2", binding.text2.text.toString())

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        binding.text1.text = savedInstanceState.getString("textView1")
        binding.text2.text = savedInstanceState.getString("textView2")

    }

    fun mensagemBemvindo(){

        val settings = getSharedPreferences("Main", MODE_PRIVATE)
        val editor = settings.edit()

        if(settings.getBoolean("primeiroAcesso", true)) {
            val toast = Toast.makeText(applicationContext, "Bem-vindo!", Toast.LENGTH_LONG)
            toast.show()

            editor.putBoolean("primeiroAcesso", false)
            editor.apply()
        }


    }
}