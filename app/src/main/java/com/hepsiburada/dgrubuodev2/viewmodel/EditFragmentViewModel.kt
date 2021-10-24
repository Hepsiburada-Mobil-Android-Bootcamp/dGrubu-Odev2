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

    init {
        firestore=Firebase.firestore
    }

    fun editRecipe(recipe: Foods, uuid: String?){


        uuid?.let {

            firestore.collection("foods").document(uuid)
                .set(recipe)
                .addOnSuccessListener { result ->
                }
                .addOnFailureListener { exception ->
                }

        }

    }




}
