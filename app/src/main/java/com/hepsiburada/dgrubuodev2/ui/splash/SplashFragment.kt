package com.hepsiburada.dgrubuodev2.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.utils.SharedPrefHelper
import com.hepsiburada.dgrubuodev2.databinding.FragmentSplashBinding
import com.hepsiburada.dgrubuodev2.ui.activity.HomeActivity

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private  var auth: FirebaseAuth? = FirebaseAuth.getInstance()

    private val lottieAnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            if (isSkipedOnboarding()) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
            }
        }

        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth?.currentUser
        if(currentUser != null){
            activity?.let {
                val intent = Intent(it, HomeActivity::class.java)
                it.finish()
                it.startActivity(intent)
            }
        }

        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.lottieAnimationView.addAnimatorListener(lottieAnimatorListener)
    }

    private fun isSkipedOnboarding(): Boolean {
        return SharedPrefHelper(requireContext()).isSkipedOnboarding()
    }

}