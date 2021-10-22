package com.hepsiburada.dgrubuodev2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hepsiburada.dgrubuodev2.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_DGrubuOdev2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}