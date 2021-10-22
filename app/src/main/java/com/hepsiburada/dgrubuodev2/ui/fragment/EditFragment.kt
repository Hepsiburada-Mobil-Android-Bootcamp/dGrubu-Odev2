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
import com.google.android.material.snackbar.Snackbar
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentEditBinding
import com.hepsiburada.dgrubuodev2.viewmodel.EditFragmentViewModel

class EditFragment : Fragment() {

    private lateinit var binding:FragmentEditBinding
    private lateinit var activityResultLauncher:ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedPicture: Uri?=null
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
        registerLauncher()
        arguments?.let {
            //uuid=DetailsFragmentArgs.fromBundle(it).foodId
        }
    }


    fun saveOnClick(){
        //editViewModel.editRecipe(Foods(uuid?,binding.editFoodNameTextField.text..))
    }


    fun selectPictureOnClick(view:View){

        if(ContextCompat.checkSelfPermission(this.requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view,"Permission needed for gallery!", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission"){
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }.show()

            }
            else{
                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

            }
        }else{
            val galleryIntent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(galleryIntent)
        }

    }

    private fun registerLauncher(){
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode==RESULT_OK){
                val result=it.data
                result?.let {
                    selectedPicture=result.data
                    selectedPicture?.let {
                        binding.editFoodImageView.setImageURI(selectedPicture)
                    }
                }
            }
        }
        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                val galleryIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(galleryIntent)
            }
            else{
                Toast.makeText(this.requireActivity(),"Permission needed!",Toast.LENGTH_LONG).show()
            }
        }

    }





}