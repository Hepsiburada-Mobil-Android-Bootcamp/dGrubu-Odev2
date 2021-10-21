package com.hepsiburada.dgrubuodev2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

import com.google.android.gms.tasks.OnCompleteListener

class RegisterViewModel : ViewModel() {
    private  var auth: FirebaseAuth? = null

    val username = MutableLiveData<String>()
    val email  = MutableLiveData<String>()
    val password  = MutableLiveData<String>()
    val confirmPassword  = MutableLiveData<String>()

    init {
        auth= FirebaseAuth.getInstance()
    }

    fun userRegister (email: String, password: String, username: String){

        auth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build()

                    val user : FirebaseUser? = auth?.currentUser
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener(OnCompleteListener<Void?> { task ->
                            if (task.isSuccessful) {
                                Log.d("profile updated", "User profile updated. Name is "+user.displayName)
                            }
                        })

                    // Sign in success, update UI with the signed-in user's information
                    Log.i("viewmodel", "createUserWithEmail:success")

                } else {
                    // If sign in fails, display a message to the user.
                    Log.i("viewmodel" ,"createUserWithEmail:failure")
                }
            }
    }
}

