package com.example.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project.databinding.FragmentHistoricoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HistoricoFragment : Fragment() {

    private lateinit var binding: FragmentHistoricoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialize a referência do Firebase Database
        database = FirebaseDatabase.getInstance().reference
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoricoBinding.inflate(inflater, container, false)
        val view = binding.root
            binding.submitButton.setOnClickListener {
                val respostas = mapOf(
                    "tratamentoTextView" to
                )
            }

            database.child("historico_medico").push().setValue(respostas)
                .addOnSuccessListener {
                    Toast.makeText(context, "Dados enviados com sucesso!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Erro ao enviar dados.", Toast.LENGTH_SHORT).show()
                }


        return view
    }

    private fun checkBoxResposta(checkBox: CheckBox, checkBox: CheckBox){

    }

}
