package com.hepsiburada.dgrubuodev2.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentRegisterBinding
import com.hepsiburada.dgrubuodev2.ui.activity.HomeActivity
import com.hepsiburada.dgrubuodev2.utils.ValidationUtil
import com.hepsiburada.dgrubuodev2.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private  var viewModel: RegisterViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.username.doOnTextChanged { text, start, before, count ->
            viewModel?.username?.value = text?.toString()

        }
        binding.email.doOnTextChanged { text, start, before, count ->
            viewModel?.email?.value = text?.toString()

        }
        binding.password.doOnTextChanged { text, start, before, count ->
            viewModel?.password?.value = text?.toString()

        }
        binding.confirmPassword.doOnTextChanged { text, start, before, count ->
            viewModel?.confirmPassword?.value = text?.toString()

        }

        viewModel?.username?.observe(viewLifecycleOwner, Observer { username ->
            binding.username.error = when {
                username.isEmpty() -> "Username is required"
                !ValidationUtil.validateUsername(username)-> "Username can only include a-z, 0-9 and _ characters"
                username.length <= 2 -> "Username is too short"
                username.length > 20 -> "Username is too long"
                else -> null
            }
        })

        viewModel?.email?.observe(viewLifecycleOwner, Observer { email ->

            binding.email.error = when {
                email.isEmpty()->"Email is required"
                !ValidationUtil.validateEmail(email)->"Email is invalid"
                email.length <= 5 -> "Email is too short"
                email.length > 50 -> "Email is too long"
                else -> null
            }
        })

        viewModel?.password?.observe(viewLifecycleOwner, Observer { password ->
            binding.password.error = when {
                password.isEmpty() -> "Password is required"
                password.length <= 7 -> "Password is to short"
                password.length > 20 -> "Password is too long"
               !ValidationUtil.validatePassword(password)->"Password must contain one digit, one lowercase letter, one uppercase letter, one special character"
                else -> null
            }
        })

        viewModel?.confirmPassword?.observe(viewLifecycleOwner, Observer { confirmPassword ->
            binding.confirmPassword.error = when {
                confirmPassword.isEmpty() -> "Re enter password"
                !viewModel?.password?.value.equals(confirmPassword)->"Passwords be same"
                else ->null
            }
        })

        binding.registerButton.setOnClickListener {
            var hasError = false
            when {
                binding.username.error.isNullOrEmpty() && viewModel?.username?.value.isNullOrEmpty() -> {
                    binding.username.error = "Username is not empty"
                    hasError = true
                }
                binding.email.error.isNullOrEmpty() && viewModel?.email?.value.isNullOrEmpty() -> {
                    binding.email.error = "Email is not empty"
                    hasError = true
                }
                binding.password.error.isNullOrEmpty() && viewModel?.password?.value.isNullOrEmpty() -> {
                    binding.password.error = "Password is not empty"
                    hasError = true
                }
                binding.confirmPassword.error.isNullOrEmpty() && viewModel?.confirmPassword?.value.isNullOrEmpty() -> {
                    binding.confirmPassword.error = "Re enter password"
                    hasError = true
                }

            }
            when {
                !hasError -> {
                    viewModel?.userRegister(
                        viewModel?.email?.value.toString(),
                        viewModel?.password?.value.toString(),
                        viewModel?.username?.value.toString()
                    )
                    activity?.let{
                        val intent = Intent (it, HomeActivity::class.java)
                        it.finish()
                        it.startActivity(intent)
                    }
                }
                else -> Toast.makeText(inflater.context, "Register unsuccesful", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}