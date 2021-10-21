package com.hepsiburada.dgrubuodev2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.hepsiburada.dgrubuodev2.R

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_DGrubuOdev2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}