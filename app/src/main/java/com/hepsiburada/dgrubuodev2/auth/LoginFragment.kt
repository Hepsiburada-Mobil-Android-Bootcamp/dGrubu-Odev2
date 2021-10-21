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
import androidx.navigation.findNavController
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentLoginBinding
import com.hepsiburada.dgrubuodev2.ui.HomeActivity
import com.hepsiburada.dgrubuodev2.utils.ValidationUtil
import com.hepsiburada.dgrubuodev2.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private var viewModel: LoginViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.email.doOnTextChanged { text, start, before, count ->
            viewModel?.email?.value = text?.toString()
        }

        binding.password.doOnTextChanged { text, start, before, count ->
            viewModel?.password?.value = text?.toString()
        }

        viewModel?.email?.observe(viewLifecycleOwner, Observer { email ->

            binding.email.error = when {
                email.isEmpty() -> "Email is required"
                else -> null
            }
        })

        viewModel?.password?.observe(viewLifecycleOwner, Observer { password ->
            binding.password.error = when {
                password.isEmpty() -> "Password is required"
                else -> null
            }
        })
        viewModel?.successLogin?.observe(viewLifecycleOwner, Observer { success ->
            if (viewModel?.successLogin?.value == true) {
                activity?.let {
                    val intent = Intent(it, HomeActivity::class.java)
                    it.finish()
                    it.startActivity(intent)
                }
                Toast.makeText(inflater.context, "Login succesful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(inflater.context, "Login unsuccesful", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnLogin.setOnClickListener {
            viewModel?.userLogin(
                viewModel?.email?.value.toString(),
                viewModel?.password?.value.toString(),
            )
        }

        binding.tvRegister.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding.root
    }

}