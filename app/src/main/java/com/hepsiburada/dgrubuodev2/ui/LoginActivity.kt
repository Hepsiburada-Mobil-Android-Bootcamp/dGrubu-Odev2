package com.hepsiburada.dgrubuodev2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private  var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }
}