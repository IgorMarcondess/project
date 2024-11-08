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

            if (!emailValido(email)) {
                Toast.makeText(context, "Email no formato incorreto.", Toast.LENGTH_SHORT).show()
            } else if (!cpfValido(cpf)) {
                Toast.makeText(context, "CPF no formato incorreto.", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(context, "Senha não pode ser vazia.", Toast.LENGTH_SHORT).show()
            } else if (!telefoneValido(telefone)) {
                Toast.makeText(context, "Somente números são permitidos no telefone.", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(email, password)
                findNavController().navigate(R.id.action_singUpFragment_to_singUp2Fragment)
            }
        }

        return binding.root
    }


    private fun emailValido(email: String): Boolean{
        if(email.contains("@") && email.contains(".com") && email.isNotEmpty()){
            return true
        }else{
            return false
        }
    }

    private fun cpfValido(cpf: String): Boolean{
        if(cpf.length == 11 && cpf.isNotEmpty()){
            return true
        }else{
            return false
        }
    }

    private fun telefoneValido(telefone: String): Boolean{
        if(telefone.all { it.isDigit() }){
            return true
        }else{
            return false
        }
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
