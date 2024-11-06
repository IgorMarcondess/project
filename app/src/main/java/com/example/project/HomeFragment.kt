package com.example.project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.example.project.databinding.FragmentHomeBinding
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.project.databinding.FragmentSingUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        if (auth.currentUser != null) {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_singUpFragment)
        }
        binding.LogInbutton.setOnClickListener {


            lifecycleScope.launch {
                val email = binding.EmailEditText.text.toString()
                val password = binding.PasswordEditText.text.toString()

                val result = auth.signInWithEmailAndPassword(email, password).await()

                if (result.user != null) {
                    findNavController().navigate(R.id.action_homeFragment_to_landingPageFragment)
                }else {
                    Toast.makeText(context, "Erro ao realizar login, verifique seus dados", Toast.LENGTH_SHORT).show()
                    Log.e("HomeFragment", "Erro ao realizar login de usuário")
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}