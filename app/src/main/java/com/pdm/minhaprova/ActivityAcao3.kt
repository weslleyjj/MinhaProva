package com.pdm.minhaprova

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import com.pdm.minhaprova.databinding.ActivityAcao3Binding
import com.pdm.minhaprova.repository.LivroDBOpener

class ActivityAcao3: AppCompatActivity() {

    lateinit var binding: ActivityAcao3Binding
    var id = 0
    private lateinit var db: LivroDBOpener
    private var sizedb = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao3)

        db = LivroDBOpener(this)
        sizedb = db.findAll().size

        proximoLivro()
        binding.buttonAnterior.isInvisible = true


        binding.buttonProximo.setOnClickListener {
            proximoLivro()
            binding.buttonProximo.isInvisible = id == sizedb
            binding.buttonAnterior.isInvisible = id == 1
        }

        binding.buttonAnterior.setOnClickListener {
            livroAnterior()
            binding.buttonProximo.isInvisible = id == sizedb
            binding.buttonAnterior.isInvisible = id == 1
        }

        binding.buttonCancelar.setOnClickListener {
            finish()
        }

    }

    private fun proximoLivro(){
        id++;
        val livro = db.findById(id)

        binding.titulo.text = livro.nome
        binding.autor.text = livro.autor
        binding.ano.text = livro.ano.toString()
        binding.barNotas.rating = livro.nota

    }

    private fun livroAnterior(){
        id--
        val livro = db.findById(id)

        binding.titulo.text = livro.nome
        binding.autor.text = livro.autor
        binding.ano.text = livro.ano.toString()
        binding.barNotas.rating = livro.nota

    }

}