package com.example.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.project.databinding.FragmentHomeBinding
import com.example.project.databinding.FragmentSingUp2Binding

class SingUp2Fragment : Fragment() {
    private var _binding: FragmentSingUp2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingUp2Binding.inflate(inflater, container, false)

        binding.next2Button.setOnClickListener {
            val endereco = binding.enderecoEditText.text.toString().trim()
            val cep = binding.cepEditText.text.toString().trim()
            val numero = binding.numeroTextView.text.toString().trim()


            if(!cepValido(cep)){
                Toast.makeText(context, "CEP no formato incorreto.", Toast.LENGTH_SHORT).show()
            } else if (endereco.isEmpty()){
                Toast.makeText(context, "Endereço no formato incorreto.", Toast.LENGTH_SHORT).show()
            } else if (numero.isEmpty()){
                Toast.makeText(context, "Número no formato incorreto.", Toast.LENGTH_SHORT).show()
            }else {
                findNavController().navigate(R.id.action_singUp2Fragment_to_historicoFragment)
            }
        }
        return binding.root
    }

    private fun cepValido(cep: String): Boolean{
        return if (cep.isNotEmpty() && cep.length == 8){
            true
        }else{
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

