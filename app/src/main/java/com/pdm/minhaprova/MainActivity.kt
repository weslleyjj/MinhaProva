package com.pdm.minhaprova

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.pdm.minhaprova.databinding.ActivityMainBinding
import com.pdm.minhaprova.repository.Livro
import com.pdm.minhaprova.repository.LivroDBOpener


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

        binding.button2.setOnClickListener {
            val dialog = SendMessageDialogFragment(applicationContext)
            dialog.isCancelable = false
            dialog.show(supportFragmentManager,"Dialog")
        }

//        val l1 = Livro(0, "A Guerra dos Mundos", "H.G Wells", 1898, 9.5f)
//        val l2 = Livro(0, "A Máquina do Tempo", "H.G Wells", 1895, 7.8f)
//
//        val db = LivroDBOpener(this)
//
//        db.insert(l1)
//        db.insert(l2)
//
//        val temp = db.findById(2)
//
//        println(temp.toString())

        binding.button3.setOnClickListener {
            val intent = Intent(this, ActivityAcao2::class.java)
            startActivityForResult(intent, 2)

        }

        binding.button4.setOnClickListener {
            val intent = Intent(this, ActivityAcao3::class.java)
            startActivity(intent)

        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){

            when(requestCode){
                1 -> {
                    val textoDigitado = data?.getStringExtra("textoDigitado").toString()
                    binding.text1.text = textoDigitado
                }
                2 -> {
                    val cadastrado = data?.getStringExtra("cadastro").toString()
                    binding.text2.text = cadastrado
                }
            }

        }else if (resultCode == Activity.RESULT_CANCELED){
            val snackbar =
                Snackbar.make(binding.root, "Cancelado", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

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