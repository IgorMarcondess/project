package com.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project.databinding.FragmentHistoricoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HistoricoFragment : Fragment() {

    private lateinit var binding: FragmentHistoricoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoricoBinding.inflate(inflater, container, false)

            binding.submitButton.setOnClickListener {
                val respostas = mapOf(
                    "tratamento" to checkBoxResposta(binding.tratamentoSim, binding.tratamentoNao),
                    "canal" to checkBoxResposta(binding.canalSim, binding.canalNao),
                    "limpeza" to checkBoxResposta(binding.limpezaSim, binding.cleaningNo),
                    "aparelho" to checkBoxResposta(binding.aparelhoSim, binding.aparelhoNao),
                    "cirurgia" to checkBoxResposta(binding.cirurgiaSim, binding.cirurgiaNao)
                )
                database.child("historico_medico").push().setValue(respostas)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Dados enviados com sucesso!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_historicoFragment_to_landingPageFragment)
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Erro ao enviar dados.", Toast.LENGTH_SHORT).show()
                    }
            }
        return binding.root
    }

    //Função utilizada para buscar resposta do checkbox e transformar em SIM ou NÃO
    private fun checkBoxResposta(checkBoxSim: CheckBox, checkBoxNao: CheckBox): String {
        if (checkBoxSim.isChecked){
            return "Sim"
        }else if (checkBoxNao.isChecked){
            return "Não"
        }else {
            return "Selecione todas as opções"
        }
    }

}
