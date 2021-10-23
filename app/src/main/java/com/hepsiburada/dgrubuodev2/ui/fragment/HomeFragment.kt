package com.hepsiburada.dgrubuodev2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        binding.floatingActionButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_homeFragment_to_addFoodFragment)
        }
        return binding.root
    }
}