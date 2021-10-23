package com.hepsiburada.dgrubuodev2.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.*

object PictureSelectionUtil {
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedPicture: Uri?=null
    private val storage: FirebaseStorage=Firebase.storage
    var downloadUrl:String?=null


    fun pictureSelection(view:View,fragment: Fragment){
        if(ContextCompat.checkSelfPermission(fragment.requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(fragment.requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                createSnackbar(view,"Permission needed for gallery!","Give Permission")
            }
            else{
                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

            }
        }else{
            activityResultLauncher.launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
        }
    }

    fun setActivityResultLauncher(fragment: Fragment,view:ImageView){

        activityResultLauncher=fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode== Activity.RESULT_OK){
                val result=it.data
                result?.let {
                    selectedPicture=result.data
                    selectedPicture?.let {
                        view.setImageURI(selectedPicture)
                    }
                }
            }
        }
    }


    fun setPermissionLauncher(fragment: Fragment){
        permissionLauncher=fragment.registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                activityResultLauncher.launch(Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
            }
            else{
                Toast.makeText(fragment.requireActivity(),"Permission needed!", Toast.LENGTH_LONG).show()
            }
        }

    }


    fun createSnackbar(view: View,text:String,actionText:String){
        Snackbar.make(view,text, Snackbar.LENGTH_INDEFINITE).setAction(actionText){
            permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }.show()

    }


     suspend fun uploadPicture(path:String,imgUri:Uri?,fileName:String?):String?= withContext(Dispatchers.IO){

        val reference=storage.reference
        val imgReference=reference.child(path).child(fileName+".jpg")

         imgUri?.let {

             val storageReference = storage.reference.child(path).child(fileName + ".jpg")
             storageReference.putFile(imgUri).addOnSuccessListener {
                     imgReference.downloadUrl.addOnSuccessListener {
                         downloadUrl = it.toString()
                         //Log.e("7777777777",downloadUrl!!)
                     }
                 }

             }

             Log.e("7777777777", downloadUrl!!)
         return@withContext downloadUrl
         }



}