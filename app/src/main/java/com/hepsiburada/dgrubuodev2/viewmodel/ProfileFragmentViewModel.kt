package com.hepsiburada.dgrubuodev2.viewmodel

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

    fun updateProfile(username: String, email: String) {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .build()

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                }
            }

        user!!.updateEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                }
            }

        /*user!!.updatePassword(password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                }
            }*/
    }

    fun logOut() {
        auth?.signOut()
    }
}