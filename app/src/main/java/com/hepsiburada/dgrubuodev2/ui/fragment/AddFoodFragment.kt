package com.hepsiburada.dgrubuodev2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentAddFoodBinding
import com.hepsiburada.dgrubuodev2.databinding.FragmentLoginBinding
import com.hepsiburada.dgrubuodev2.viewmodel.AddFoodFragmentViewModel
import com.hepsiburada.dgrubuodev2.viewmodel.LoginViewModel

class AddFoodFragment : Fragment() {

    private var viewModel: AddFoodFragmentViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAddFoodBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_food, container, false
        )

        viewModel = ViewModelProvider(this).get(AddFoodFragmentViewModel::class.java)


        viewModel?.foodName?.observe(viewLifecycleOwner, Observer { foodName ->

            binding.editAddFoodNameTextLayout.error = when {
                foodName.isEmpty() -> "This field is required"
                else -> null
            }
        })

        viewModel?.foodCalory?.observe(viewLifecycleOwner, Observer { foodCal ->

            binding.editAddFoodCalorieTextLayout.error = when {
                foodCal==null -> "This field is required"
                else -> null
            }
        })

        viewModel?.foodScore?.observe(viewLifecycleOwner, Observer { foodScore ->

            binding.editAddScoreTextLayout.error = when {
                foodScore==null -> "This field is required"
                else -> null
            }
        })

        viewModel?.foodCategory?.observe(viewLifecycleOwner, Observer { foodCat ->

            binding.editAddFoodCategoryTextLayout.error = when {
                foodCat.isEmpty() -> "This field is required"
                else -> null
            }
        })

        viewModel?.foodIngredients?.observe(viewLifecycleOwner, Observer { foodIng ->

            binding.editAddFoodIngredientsTextLayout.error = when {
                foodIng.isEmpty() -> "This field is required"
                else -> null
            }
        })

        viewModel?.foodRecipe?.observe(viewLifecycleOwner, Observer { foodRecipe ->

            binding.editAddFoodDirectionsTextLayout.error = when {
                foodRecipe.isEmpty() -> "This field is required"
                else -> null
            }
        })

        binding.addButton.setOnClickListener {
            viewModel?.saveFood(
                viewModel?.foodName?.value.toString(),
                viewModel?.foodCategory?.value.toString(),
                viewModel?.foodCalory?.value?:0,
                viewModel?.foodIngredients?.value.toString(),
                viewModel?.foodRecipe?.value.toString(),
                viewModel?.foodScore?.value?:0
            )
        }

        return binding.root
    }
}