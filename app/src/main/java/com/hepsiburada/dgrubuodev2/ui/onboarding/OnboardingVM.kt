package com.hepsiburada.dgrubuodev2.ui.onboarding

import androidx.lifecycle.ViewModel
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.data.model.OnboardingItem

class OnboardingVM : ViewModel() {

    val sliderList: Array<OnboardingItem> = arrayOf(
        OnboardingItem(
            resId = R.drawable.img_recipe_1,
            title = "Lorem Ipsum"
        ),
        OnboardingItem(
            resId = R.drawable.img_recipe_3,
            title = "Lorem Ipsum"
        ),
        OnboardingItem(
            resId = R.drawable.img_recipe_2,
            title = "Lorem Ipsum"
        )
    )
}