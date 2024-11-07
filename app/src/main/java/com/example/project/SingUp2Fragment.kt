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
        // Inflate the layout for this fragment
        _binding = FragmentSingUp2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun EmailValido(): Boolean {
            var email = binding.email2EditText.text.toString().trim()

            if (email.contains("@") && email.contains(".com")) {
                return true
            } else {
                Toast.makeText(context, "Email no formato incorreto.", Toast.LENGTH_SHORT).show()
                return false
            }
        }

        fun validarCEP(): Boolean {
            val cep = binding.cepEditText.text.toString().trim()

            if (cep.length == 9) {
                return true
            } else {
                Toast.makeText(context, "CEP no formato incorreto.", Toast.LENGTH_SHORT).show()
                return false
            }
        }

        fun validarNumero(): Boolean {
            val num = binding.cpfEditText.text.toString().trim()

            //VALIDANDO SE O CAMPO TEM STRING COM "it.isDigit()"
            if (num.all { it.isDigit() }) {
                return true
            } else {
                Toast.makeText(context, "Somente números são permitidos.", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        }

        if (validarNumero() == true ) {
            binding.next2Button.setOnClickListener {
                findNavController().navigate(R.id.action_singUp2Fragment_to_historicoFragment)
            }
        } else {
            Toast.makeText(context, "Campos incorretos. Verificar!", Toast.LENGTH_SHORT).show()
        }

    }
}

