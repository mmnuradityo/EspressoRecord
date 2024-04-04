package com.aditya.espressorecord.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.aditya.espressorecord.ui.Main.MainActivity
import com.aditya.espressorecord.data.SharedLogin
import com.aditya.espressorecord.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedLogin = SharedLogin(this)
        val pair = sharedLogin.getLogin()

        if (pair.first.isEmpty() && pair.second.isEmpty()) {
            binding.btnLogin.setOnClickListener {
                val userName = binding.etUserName.text
                val password = binding.etPassword.text

                if (!(userName.isEmpty() || password.isEmpty())) {
                    saveLogin(userName.toString(), password.toString())
                    showMainView(userName.toString(), password.toString())
                }
            }
        } else {
            Log.i( "loginOnCreate: ", pair.first)
            showMainView(pair.first, pair.second)
        }
    }

    private fun showMainView(userName: String, password: String) {
        startActivity(
            MainActivity.generateIntent(this, userName, password)
        )
    }

    private fun saveLogin(userName: String, password: String) {
        val sharedLogin = SharedLogin(this)
        sharedLogin.setLogin(userName, password)
    }
}