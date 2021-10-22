package com.hepsiburada.dgrubuodev2.viewmodel



import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.hepsiburada.dgrubuodev2.data.model.Foods

class EditFragmentViewModel:ViewModel() {

    private val firestore: FirebaseFirestore
    private val storage: FirebaseStorage

    init {
        firestore=Firebase.firestore
        storage= Firebase.storage
    }

    fun editRecipe(recipe: Foods, uuid: String?):Boolean?{

        var success:Boolean?=null

        uuid?.let {

            firestore.collection("foods").document(uuid)
                .set(recipe)
                .addOnSuccessListener { result ->
                    success=true
                }
                .addOnFailureListener { exception ->
                    success=false
                }

        }
        return success
    }

    fun uploadPicture(imgUri:Uri?,uuid:String?):String?{
        var downloadUrl:String?=null
        val reference=storage.reference
        val imgReference=reference.child(uuid+".jpg")

        imgUri?.let {
            imgReference.putFile(imgUri).addOnSuccessListener {
                imgReference.downloadUrl.addOnSuccessListener {
                    downloadUrl=it.toString()
                }

            }
        }
        return downloadUrl
    }




}
