package com.hepsiburada.dgrubuodev2.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.SharedPrefHelper
import com.hepsiburada.dgrubuodev2.databinding.FragmentOnboardingBinding
import com.hepsiburada.dgrubuodev2.ui.adapter.ViewPagerFragmentAdapter
import com.hepsiburada.dgrubuodev2.ui.splash.SplashVM
import com.rd.animation.type.AnimationType

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val viewPagerAdapter = ViewPagerFragmentAdapter(
            childFragmentManager,
            lifecycle
        )
        binding.pageIndicator.count = viewModel.sliderList.size
        viewModel.sliderList.forEach {
            val fragment = ItemSplashOnboardingFragment(it.resId, it.title)
            viewPagerAdapter.addFragment(fragment)
        }

        binding.tvSkip.setOnClickListener {
            SharedPrefHelper(requireContext()).saveOnboardingSkip()
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }
        binding.viewPager2.adapter = viewPagerAdapter

        //Page indicator --> https://github.com/romandanylyk/PageIndicatorView
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.pageIndicator.selection = position
                binding.pageIndicator.setAnimationType(AnimationType.WORM)
            }
        })
    }
}