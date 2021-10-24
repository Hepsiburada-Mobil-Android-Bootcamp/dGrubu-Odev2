package com.hepsiburada.dgrubuodev2.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.data.model.Foods
import com.hepsiburada.dgrubuodev2.databinding.FragmentEditBinding
import com.hepsiburada.dgrubuodev2.utils.PictureSelectionUtil
import com.hepsiburada.dgrubuodev2.viewmodel.EditFragmentViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class EditFragment : Fragment() {

    lateinit var binding:FragmentEditBinding
    var uuid:String?=null
    var currentFood:Foods?=null
    private val storage: FirebaseStorage = Firebase.storage


    val editViewModel: EditFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_edit, container, false)
        binding.editFragment=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PictureSelectionUtil.setActivityResultLauncher(this@EditFragment,binding.editFoodImageView)
        PictureSelectionUtil.setPermissionLauncher(this@EditFragment)

        arguments?.let {
            uuid = DetailsFragmentArgs.fromBundle(it).uuid
            currentFood=DetailsFragmentArgs.fromBundle(it).food
            setViews(currentFood!!)
        }
    }

    private fun setViews(currentFood: Foods) {
        binding.apply {
            editFoodNameTextField.setText(currentFood.foodName)
            editCookingTimeField.setText(currentFood.foodCookingTime.toString())
            editCategoryTextField.setText(currentFood.foodCategory)
            editIngredientsTextField.setText(currentFood.foodIngredients)
            editDirectionsTextField.setText(currentFood.foodRecipe)
            editKcalTextField.setText(currentFood.foodCalory.toString())
        }
        Picasso.get().load(currentFood.foodImg).into(binding.editFoodImageView)
    }


    fun saveOnClick(){

        PictureSelectionUtil.selectedPicture?.let {
            val imgReference=storage.reference.child("foodImg").child(binding.editFoodNameTextField.text.toString()+".jpg")
            imgReference.putFile(it).addOnSuccessListener {
                imgReference.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()
                    binding.apply {
                            currentFood= Foods("id",editFoodNameTextField.text.toString(),editCategoryTextField.text.toString(),editKcalTextField.text.toString().toInt(),editIngredientsTextField.text.toString(),editDirectionsTextField.text.toString(),editCookingTimeField.text.toString().toInt(),downloadUrl)
                            editViewModel.editRecipe(currentFood!!,uuid)
                    }
                }
            }

        }
        findNavController().navigate(EditFragmentDirections.actionEditFragmentToDetailsFragment(uuid,currentFood!!))

    }

    fun backNavOnClick(){
        val action=EditFragmentDirections.actionEditFragmentToDetailsFragment(uuid,currentFood!!)
        Navigation.findNavController(requireView()).navigate(action)
    }

    fun selectPictureOnClick(view:View){
        PictureSelectionUtil.pictureSelection(view,this@EditFragment)
    }




}