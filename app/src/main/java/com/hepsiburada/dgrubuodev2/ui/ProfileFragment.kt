package com.hepsiburada.dgrubuodev2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentProfileBinding
import com.hepsiburada.dgrubuodev2.databinding.FragmentSplashBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.btnUpdate.setOnClickListener {
            //Todo Gerekli validasyonlardan sonra bilgiler güncellenecek
        }

        binding.tvLogout.setOnClickListener {
            //TODO Logout işlemi yapılacak
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
}