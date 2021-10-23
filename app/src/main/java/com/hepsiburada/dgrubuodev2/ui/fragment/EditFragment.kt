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
import com.google.android.material.snackbar.Snackbar
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentEditBinding
import com.hepsiburada.dgrubuodev2.utils.PictureSelectionUtil
import com.hepsiburada.dgrubuodev2.viewmodel.EditFragmentViewModel

class EditFragment : Fragment() {

    lateinit var binding:FragmentEditBinding
    private lateinit var activityResultLauncher:ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    val uuid:String?=null


    val editViewModel: EditFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.editFragment=this
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_edit, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PictureSelectionUtil.setActivityResultLauncher(this@EditFragment,binding.editFoodImageView)
        PictureSelectionUtil.setPermissionLauncher(this@EditFragment)

        arguments?.let {
            //uuid=DetailsFragmentArgs.fromBundle(it).foodId
        }
    }


    fun saveOnClick(){
        PictureSelectionUtil.selectedPicture?.let {
            val downloadUrl=PictureSelectionUtil.uploadPicture("foodImg",PictureSelectionUtil.selectedPicture,uuid)
            downloadUrl?.let {
                binding.apply {
                    //editViewModel.editRecipe(Foods("id",editFoodNameTextField.text.toString(),editCategoryNameTextfield.text.toString(),editCalorieTextField.text.toString().toInt(),editIngredientsTextField.text.toString(),editDirectionsTextField.text.toString(),downloadUrl),uuid)
                }
            }
        }

    }

    fun backNavOnClick(){
        val action=EditFragmentDirections.actionEditFragmentToDetailsFragment(uuid)
        Navigation.findNavController(requireView()).navigate(action)
    }

    fun selectPictureOnClick(view:View){
        PictureSelectionUtil.pictureSelection(view,this@EditFragment)
    }




}