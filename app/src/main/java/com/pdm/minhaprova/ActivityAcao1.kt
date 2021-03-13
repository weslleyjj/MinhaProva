package com.pdm.minhaprova

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pdm.minhaprova.databinding.ActivityAcao1Binding

class ActivityAcao1: AppCompatActivity() {

    lateinit var binding: ActivityAcao1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao1)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)


        binding.buttonOK.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            val bundle = Bundle()

            bundle.putString("textoDigitado", binding.resposta.text.toString())
            intent.putExtras(bundle)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.buttonCancelar.setOnClickListener {

            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
    }

}