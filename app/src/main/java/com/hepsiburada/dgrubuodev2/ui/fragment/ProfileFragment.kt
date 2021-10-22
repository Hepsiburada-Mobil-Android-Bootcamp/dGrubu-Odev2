package com.hepsiburada.dgrubuodev2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hepsiburada.dgrubuodev2.databinding.FragmentProfileBinding
import com.hepsiburada.dgrubuodev2.viewmodel.ProfileFragmentViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var viewModel: ProfileFragmentViewModel? = null

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
        initClickListener()
        setPersonalInfo()
    }

    private fun initClickListener() {
        binding.btnUpdate.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            viewModel?.updateProfile(
                name,
                email
            )
        }

        binding.tvLogout.setOnClickListener {
            viewModel?.logOut()
        }

        binding.ivNamePen.setOnClickListener {
            binding.etName.isEnabled = !binding.etName.isEnabled
        }

        binding.ivEmailPen.setOnClickListener {
            binding.ivEmailPen.isEnabled = !binding.ivEmailPen.isEnabled
        }

        binding.ivPasswordPen.setOnClickListener {
            binding.ivPasswordPen.isEnabled = !binding.ivPasswordPen.isEnabled
        }

        binding.profileImage.setOnClickListener {
            //Todo galeriden fotoğraf seçme işlemleri
        }
    }

    private fun setPersonalInfo() {
        viewModel?.username?.observe(viewLifecycleOwner, {
            binding.etName.setText(it)
        })
        viewModel?.email?.observe(viewLifecycleOwner, {
            binding.etEmail.setText(it)
        })
    }
}