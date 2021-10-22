package com.hepsiburada.dgrubuodev2.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hepsiburada.dgrubuodev2.data.model.Foods

class DetailsFragmentViewModel:ViewModel() {

    private val firestore:FirebaseFirestore

    init {
        firestore= Firebase.firestore
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