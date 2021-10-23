package com.hepsiburada.dgrubuodev2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class ProfileFragmentViewModel : ViewModel() {
    private var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    var username = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var username1 = MutableLiveData<String>()
    var email1 = MutableLiveData<String>()
    var password1 = MutableLiveData<String>()
    var confirmPassword1 = MutableLiveData<String>()

    init {
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser
        getPersonalInfo()
    }

    private fun getPersonalInfo() {
        user.let {
            username.postValue(user?.displayName.toString())
            email.postValue(user?.email.toString())
        }
    }

    fun updateProfile() {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(username1.value)
            .build()

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                }
            }

        email1.value?.let {
            user!!.updateEmail(it)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("test123", "Success")
                    }
                }
        }

        password1.value?.let {
            user!!.updatePassword(it)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                    }
                }
        }
    }

    fun logOut(): Boolean {
        auth?.signOut()
        return true
    }
}