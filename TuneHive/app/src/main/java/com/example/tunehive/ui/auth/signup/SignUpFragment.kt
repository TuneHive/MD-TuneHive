package com.example.tunehive.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tunehive.R
import com.example.tunehive.ui.auth.login.LoginFragment

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        val usernameEditText: EditText = view.findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = view.findViewById(R.id.passwordEditText)
        val signUpButton: Button = view.findViewById(R.id.signupButton)
        val loginButton: Button = view.findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_container, LoginFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        signUpButton.setOnClickListener {
            val email = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signUp(email, password)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.signUpResult.observe(viewLifecycleOwner) { response ->
            //ganti sesuai dengan response api
            if (response.email !=null) {
                Toast.makeText(requireContext(), "Sign-up successful!, Go Login!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Sign Up Gagal", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}