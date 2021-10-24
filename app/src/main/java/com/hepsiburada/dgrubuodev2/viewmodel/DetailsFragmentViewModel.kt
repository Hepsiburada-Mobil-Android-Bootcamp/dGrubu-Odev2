package com.hepsiburada.dgrubuodev2.viewmodel


import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.hepsiburada.dgrubuodev2.data.model.Foods
import com.hepsiburada.dgrubuodev2.ui.fragment.HomeFragmentDirections


class DetailsFragmentViewModel:ViewModel() {

    private val firestore:FirebaseFirestore
    private val storage:FirebaseStorage


    init {
        firestore= Firebase.firestore
        storage= Firebase.storage
    }

    fun deleteRecipe(uuid: String?) {

        uuid?.let {

            firestore.collection("foods").document(uuid)
                .delete()
                .addOnSuccessListener { result ->

                }
                .addOnFailureListener { exception ->

                }
        }
    }



}