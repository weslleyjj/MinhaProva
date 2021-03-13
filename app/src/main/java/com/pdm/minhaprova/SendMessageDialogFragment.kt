package com.pdm.minhaprova

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class SendMessageDialogFragment(context: Context): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //uso de uma classe com padrão builder para construção da caixa de diálogo
        val builder= AlertDialog.Builder(activity!!)
        builder.setTitle("Pergunta importante")

        builder.setMessage("Gostaria de uma xícara de café?")
            .setPositiveButton("Sim", DialogInterface.OnClickListener{ dialog, id ->
                val toast = Toast.makeText(context, "Ótimo", Toast.LENGTH_LONG)
                toast.show()
            })
            .setNegativeButton("Não", DialogInterface.OnClickListener{ dialog, id ->
                val toast = Toast.makeText(context, "Fica para a próxima", Toast.LENGTH_LONG)
                toast.show()
            })

        return builder.create()
    }
}