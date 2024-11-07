package com.example.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project.databinding.FragmentSingUpBinding
import com.google.firebase.auth.FirebaseAuth

class SingUpFragment : Fragment() {

    private var _binding: FragmentSingUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingUpBinding.inflate(inflater, container, false)

        binding.nextButton.setOnClickListener {
            val cpf = binding.cpfEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val telefone = binding.phoneEditText.text.toString().trim()

            fun EmailValido(): Boolean {
                var email = binding.emailEditText.text.toString().trim()

                if (email.contains("@") && email.contains(".com") && email.isNotEmpty()) {
                    return true
                } else {
                    Toast.makeText(context, "Email no formato incorreto.", Toast.LENGTH_SHORT).show()
                    return false
                }
            }

            fun CPFValido(): Boolean {
                var cpf = binding.cpfEditText.text.toString().trim()

                if (cpf.length == 11 && cpf.isNotEmpty()) {
                    return true
                } else {
                    Toast.makeText(context, "CPF no formato incorreto.", Toast.LENGTH_SHORT).show()
                    return false
                }
            }

            fun validarNumero(): Boolean {
                val num = binding.phoneEditText.text.toString().trim()

                //VALIDANDO SE O CAMPO TEM STRING COM "it.isDigit()"
                if (num.all { it.isDigit() }) {
                    return true
                } else {
                    Toast.makeText(context, "Somente números são permitidos.", Toast.LENGTH_SHORT)
                        .show()
                    return false
                }
            }

            if (EmailValido() == true && password.isNotEmpty() && CPFValido() == true && validarNumero() == true) {
                registerUser(email, password)
                findNavController().navigate(R.id.action_singUpFragment_to_singUp2Fragment)
            } else {
                Toast.makeText(context, "Por favor, preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Cadastro bem-sucedido", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Erro ao realizar cadastro", Toast.LENGTH_SHORT).show()
                    Log.e("SignUpFragment", "Erro ao realizar cadastro de usuário", task.exception)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
