package com.hepsiburada.dgrubuodev2.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.hepsiburada.dgrubuodev2.data.model.Foods

class AddFoodFragmentViewModel: ViewModel() {

    private var firestore: FirebaseFirestore? = null

    val foodName = MutableLiveData<String>()
    val foodCalory = MutableLiveData<Int>()
    val foodScore = MutableLiveData<Int>()
    val foodIngredients = MutableLiveData<String>()
    val foodRecipe = MutableLiveData<String>()
    val foodCategory = MutableLiveData<String>()

    fun saveFood(foodName: String, foodCategory:String, foodCalory: Int,foodIngredients: String,foodRecipe: String, foodScore: Int) {
        val foods = Foods("1",foodName ,foodCategory, foodCalory, foodIngredients,foodRecipe, foodScore,"")

        firestore?.collection("foods")?.add(foods)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true ->  Log.d(ContentValues.TAG, "yemek ekleme:success")
                false ->  Log.d(ContentValues.TAG, "yemek ekleme:false")
            }
        }
    }

}