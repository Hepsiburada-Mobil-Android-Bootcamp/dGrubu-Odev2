package com.hepsiburada.dgrubuodev2.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.hepsiburada.dgrubuodev2.data.model.Foods

class DetailsFragmentViewModel:ViewModel() {

    private val firestore:FirebaseFirestore

    init {
        firestore= FirebaseFirestore.getInstance()
    }



    fun deleteRecipe(uuid: String?) {

        uuid?.let {

            firestore.collection("recipes").document(uuid)
                .delete()
                .addOnSuccessListener { result ->

                }
                .addOnFailureListener { exception ->

                }

        }

    }

    fun getRecipe(uuid: String?):Foods{

        lateinit var doc:Foods

        uuid?.let {

        firestore.collection("recipes").document(uuid)
            .get()
            .addOnSuccessListener { result ->
                val doc=result.toObject(Foods::class.java)
            }
            .addOnFailureListener { exception ->

            }

        }


        return doc
    }



}