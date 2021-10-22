package com.hepsiburada.dgrubuodev2.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private var auth: FirebaseAuth? = null

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    var successLogin = MutableLiveData<Boolean>()

    init {
        auth = FirebaseAuth.getInstance()
    }

    fun userLogin(email: String, password: String) {

        auth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    successLogin.value = true
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    successLogin.value = false
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
    }

}

