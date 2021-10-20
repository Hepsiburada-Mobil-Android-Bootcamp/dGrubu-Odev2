package com.hepsiburada.dgrubuodev2.ui.splash

import androidx.lifecycle.ViewModel
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.SharedPrefHelper
import com.hepsiburada.dgrubuodev2.data.model.OnboardingItem

class SplashVM(private val sharedPref: SharedPrefHelper) : ViewModel() {

    fun onBoardingSkiped() {
        sharedPref.saveOnboardingSkip()
    }

    fun isSkipedOnboarding(): Boolean {
        return sharedPref.isSkipedOnboarding()
    }


}