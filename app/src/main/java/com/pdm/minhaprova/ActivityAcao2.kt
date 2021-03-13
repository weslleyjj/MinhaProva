package com.pdm.minhaprova

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pdm.minhaprova.databinding.ActivityAcao2Binding
import com.pdm.minhaprova.repository.Livro
import com.pdm.minhaprova.repository.LivroDBOpener

class ActivityAcao2: AppCompatActivity() {

    private lateinit var binding: ActivityAcao2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao2)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)

        binding.buttonSalvar.setOnClickListener {

            val titulo = binding.editTitulo.text.toString()
            val autor = binding.editAutor.text.toString()
            val ano = Integer.parseInt(binding.editAno.text.toString())
            val nota = binding.barNotas.rating

            if (validarCampos(titulo, autor, ano, nota)){
                val db = LivroDBOpener(this)

                val livro = Livro(0,titulo,autor,ano,nota)

                db.insert(livro)

                //println(db.findAll().toString())

                val intent = Intent(this, MainActivity::class.java)
                val bundle = Bundle()

                bundle.putString("cadastro", "Cadastrado")
                intent.putExtras(bundle)

                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                val toast = Toast.makeText(applicationContext, "Preencha todos os campos", Toast.LENGTH_SHORT)
                toast.show()
            }

            binding.buttonCancelar.setOnClickListener {
                finish()
            }

        }

        /*
        * O  botão Salvar insere  no  banco e  volta  para  a  ActivityMain  alterando  o  R.id.text2
        * para  cadastrado.Cancelar  volta para tela de ActivityMain sem salvar e sem
        * fazer qualquer alteração
        * */

    }

    fun validarCampos(titulo: String, autor:String, ano:Int, nota: Float): Boolean{
        return (titulo.isNotBlank() && autor.isNotBlank() && ano > 0 && nota >= 0)
    }
}