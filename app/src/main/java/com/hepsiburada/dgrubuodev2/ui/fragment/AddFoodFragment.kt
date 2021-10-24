package com.hepsiburada.dgrubuodev2.ui.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentAddFoodBinding
import com.hepsiburada.dgrubuodev2.utils.PictureSelectionUtil
import com.hepsiburada.dgrubuodev2.utils.PictureSelectionUtil.downloadUrl
import com.hepsiburada.dgrubuodev2.utils.ValidationUtil
import com.hepsiburada.dgrubuodev2.viewmodel.AddFoodFragmentViewModel
import kotlinx.coroutines.*

class AddFoodFragment : Fragment() {

    private var viewModel: AddFoodFragmentViewModel? = null
    private var downloadUrl: String? = ""
    private val storage: FirebaseStorage = Firebase.storage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentAddFoodBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_food, container, false
        )
        PictureSelectionUtil.setActivityResultLauncher(this@AddFoodFragment, binding.editFoodImageView)
        PictureSelectionUtil.setPermissionLauncher(this@AddFoodFragment)

        viewModel = ViewModelProvider(this).get(AddFoodFragmentViewModel::class.java)


        binding.editFoodImageView.setOnClickListener {
            PictureSelectionUtil.pictureSelection(it, this@AddFoodFragment)
        }


        binding.addfoodName.doOnTextChanged { text, start, before, count ->
            viewModel?.foodName?.value = text?.toString()

        }
        binding.addfoodCalorie.doOnTextChanged { text, start, before, count ->
            viewModel?.foodCalory?.value = text?.toString()?.toInt()

        }
        binding.addfoodCategory.doOnTextChanged { text, start, before, count ->
            viewModel?.foodCategory?.value = text?.toString()

        }
        binding.addfoodRecipe.doOnTextChanged { text, start, before, count ->
            viewModel?.foodRecipe?.value = text?.toString()

        }
        binding.addfoodIngredients.doOnTextChanged { text, start, before, count ->
            viewModel?.foodIngredients?.value = text?.toString()

        }
        binding.addfoodCookingTime.doOnTextChanged { text, start, before, count ->
            viewModel?.foodCookingTime?.value = text?.toString()?.toInt()

        }

        binding.addButton.setOnClickListener {
            var hasError = false
            when {
                binding.addfoodName.error.isNullOrEmpty() && viewModel?.foodName?.value.isNullOrEmpty() -> {
                   ValidationUtil.validateTextField(binding.addfoodName)
                    hasError = true
                }
                binding.addfoodCategory.error.isNullOrEmpty() && viewModel?.foodCategory?.value.isNullOrEmpty() -> {
                    ValidationUtil.validateTextField(binding.addfoodCategory)
                    hasError = true
                }
                binding.addfoodIngredients.error.isNullOrEmpty() && viewModel?.foodIngredients?.value.isNullOrEmpty() -> {
                   ValidationUtil.validateTextField(binding.addfoodIngredients)
                    hasError = true
                }
                binding.addfoodRecipe.error.isNullOrEmpty() && viewModel?.foodRecipe?.value.isNullOrEmpty() -> {
                    ValidationUtil.validateTextField(binding.addfoodRecipe)
                    hasError = true
                }
            }
            when {
                !hasError -> {

                        PictureSelectionUtil.selectedPicture?.let {

                        val imgReference=storage.reference.child("foodImg").child(binding.addfoodName.text.toString()+".jpg")

                            imgReference.putFile(it).addOnSuccessListener {
                                imgReference.downloadUrl.addOnSuccessListener {
                                        downloadUrl = it.toString()
                                        viewModel?.saveFood(
                                            foodName = viewModel?.foodName?.value.toString(),
                                            foodCategory = viewModel?.foodCategory?.value.toString(),
                                            foodCalory = viewModel?.foodCalory?.value ?: 0,
                                            foodIngredients = viewModel?.foodIngredients?.value.toString(),
                                            foodRecipe = viewModel?.foodRecipe?.value.toString(),
                                            foodCookingTime = viewModel?.foodCookingTime?.value ?: 0,
                                            foodImg = downloadUrl!!
                                        )

                                    }
                                }

                }
                    view?.findNavController()?.navigate(R.id.action_addFoodFragment_to_homeFragment)
                }
                else -> Toast.makeText(inflater.context, "Add food unsuccesful", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }




}