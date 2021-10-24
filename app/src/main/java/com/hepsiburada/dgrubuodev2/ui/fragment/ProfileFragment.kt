package com.hepsiburada.dgrubuodev2.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentProfileBinding
import com.hepsiburada.dgrubuodev2.utils.PictureSelectionUtil
import com.hepsiburada.dgrubuodev2.utils.ValidationUtil
import com.hepsiburada.dgrubuodev2.viewmodel.ProfileFragmentViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var viewModel: ProfileFragmentViewModel? = null

    private val uuid: String? = null
    private var downloadUrl: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ProfileFragmentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPersonalInfo()
        setVMVariable()
        showWarning()
        initClickListener()
        setEditable()
        PictureSelectionUtil.setActivityResultLauncher(this@ProfileFragment, binding.profileImage)
        PictureSelectionUtil.setPermissionLauncher(this@ProfileFragment)
    }

    private fun initClickListener() {
        binding.btnUpdate.setOnClickListener {
            if (!isError()) {

                CoroutineScope(Dispatchers.IO).async {

                    PictureSelectionUtil.selectedPicture?.let {
                        downloadUrl = PictureSelectionUtil.uploadPicture(
                            "profileImg",
                            PictureSelectionUtil.selectedPicture,
                            binding.etName.text.toString()
                        )
                    }

                    downloadUrl?.let { url -> viewModel?.updateProfile(url) }
                }
            }


        }

        binding.tvLogout.setOnClickListener {
            if (viewModel?.logOut() == true) {
                view?.findNavController()?.navigate(R.id.action_global_loginFragment2)
            }
        }

        binding.profileImage.setOnClickListener {
            view?.let { img -> PictureSelectionUtil.pictureSelection(img, this@ProfileFragment) }
        }
    }

    private fun setVMVariable() {
        binding.etName.doOnTextChanged { text, start, before, count ->
            viewModel?.username1?.value = text?.toString()
        }

        binding.etEmail.doOnTextChanged { text, start, before, count ->
            viewModel?.email1?.value = text?.toString()
        }

        binding.etPassword.doOnTextChanged { text, start, before, count ->
            viewModel?.password1?.value = text?.toString()
        }

        binding.etConfirmPassword.doOnTextChanged { text, start, before, count ->
            viewModel?.confirmPassword1?.value = text?.toString()
        }
    }

    private fun showWarning() {
        viewModel?.username1?.observe(viewLifecycleOwner, Observer { username1 ->
            binding.etName.error = when {
                username1.isEmpty() -> "Username is required"
                !ValidationUtil.validateUsername(username1) -> "Username can only include a-z, 0-9 and _ characters"
                username1.length <= 2 -> "Username is too short"
                username1.length > 20 -> "Username is too long"
                else -> null
            }
        })

        viewModel?.email1?.observe(viewLifecycleOwner, Observer { email1 ->
            binding.etEmail.error = when {
                email1.isEmpty() -> "Email is required"
                !ValidationUtil.validateEmail(email1) -> "Email is invalid"
                email1.length <= 5 -> "Email is too short"
                email1.length > 50 -> "Email is too long"
                else -> null
            }
        })

        viewModel?.password1?.observe(viewLifecycleOwner, Observer { password1 ->
            binding.etPassword.error = when {
                password1.isEmpty() -> "Password is required"
                password1.length <= 7 -> "Password is to short"
                password1.length > 20 -> "Password is too long"
                !ValidationUtil.validatePassword(password1) -> "Password must contain one digit, one lowercase letter, one uppercase letter, one special character"
                else -> null
            }
        })

        viewModel?.confirmPassword1?.observe(viewLifecycleOwner, Observer { confirmPassword1 ->
            binding.etConfirmPassword.error = when {
                confirmPassword1.isEmpty() -> "Re enter password"
                !viewModel?.password1?.value.equals(confirmPassword1) -> "Passwords be same"
                else -> null
            }
        })
    }

    private fun setPersonalInfo() {
        viewModel?.username?.observe(viewLifecycleOwner, {
            binding.etName.setText(it)
        })
        viewModel?.email?.observe(viewLifecycleOwner, {
            binding.etEmail.setText(it)
        })
        viewModel?.profileImage?.observe(viewLifecycleOwner, {
           // Picasso.get().load(viewModel!!.profileImage.toString()).into(binding.profileImage)
        })
    }

    private fun setEditable() {
        binding.ivNamePen.setOnClickListener {
            binding.etName.isEnabled = !binding.etName.isEnabled
        }

        binding.ivEmailPen.setOnClickListener {
            binding.etEmail.isEnabled = !binding.etEmail.isEnabled
        }
    }

    private fun isError(): Boolean {
        var hasError = false
        when {
            binding.etName.error.isNullOrEmpty() && viewModel?.username1?.value.isNullOrEmpty() -> {
                binding.etName.error = "Username is not empty"
                hasError = true
            }
            binding.etEmail.error.isNullOrEmpty() && viewModel?.email1?.value.isNullOrEmpty() -> {
                binding.etEmail.error = "Email is not empty"
                hasError = true
            }
            binding.etPassword.error.isNullOrEmpty() && viewModel?.password1?.value.isNullOrEmpty() -> {
                binding.etPassword.error = "Password is not empty"
                hasError = true
            }
            binding.etConfirmPassword.error.isNullOrEmpty() && viewModel?.confirmPassword1?.value.isNullOrEmpty() -> {
                binding.etConfirmPassword.error = "Re enter password"
                hasError = true
            }
        }
        return hasError
    }
}