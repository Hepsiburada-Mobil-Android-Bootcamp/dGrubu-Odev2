package com.hepsiburada.dgrubuodev2.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.hepsiburada.dgrubuodev2.R

class HomeActivity : AppCompatActivity() {

    private  var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_DGrubuOdev2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth?.currentUser
        if(currentUser != null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}