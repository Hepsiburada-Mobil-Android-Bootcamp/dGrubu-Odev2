package com.hepsiburada.dgrubuodev2.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.hepsiburada.dgrubuodev2.data.model.Foods

class AddFoodFragmentViewModel: ViewModel() {

    private var firestore: FirebaseFirestore? = null
    private  var auth: FirebaseAuth? = null

    val foodName = MutableLiveData<String>()
    val foodCalory = MutableLiveData<Int>()
    val foodCookingTime = MutableLiveData<Int>()
    val foodIngredients = MutableLiveData<String>()
    val foodRecipe = MutableLiveData<String>()
    val foodCategory = MutableLiveData<String>()

    init {
        firestore = FirebaseFirestore.getInstance()
        auth= FirebaseAuth.getInstance()
    }

    fun saveFood(foodName: String, foodCategory:String, foodCalory: Int, foodIngredients: String, foodRecipe: String, foodCookingTime: Int) {
        val user : FirebaseUser? = auth?.currentUser
        val userID: String = user?.uid.toString()

        val foods = Foods("1",foodName ,foodCategory, foodCalory, foodIngredients,foodRecipe, foodCookingTime,"")
        val refUser = firestore?.collection("foods")

                refUser?.add(foods)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true ->  Log.d(ContentValues.TAG, "yemek ekleme:success")
                false ->  Log.d(ContentValues.TAG, "yemek ekleme:false")
            }
        }
    }
}