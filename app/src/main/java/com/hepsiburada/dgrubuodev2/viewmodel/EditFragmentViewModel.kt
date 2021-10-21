package com.hepsiburada.dgrubuodev2.viewmodel



import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.hepsiburada.dgrubuodev2.data.model.Foods

class EditFragmentViewModel:ViewModel() {

    private val firestore: FirebaseFirestore

    init {
        firestore= FirebaseFirestore.getInstance()
    }

    fun editRecipe(recipe: Foods, uuid: String?) {

        uuid?.let {

            firestore.collection("recipes").document(uuid)
                .set(recipe)
                .addOnSuccessListener { result ->

                }
                .addOnFailureListener { exception ->

                }

        }

    }


}
