package com.aditya.espressorecord.ui.Main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aditya.espressorecord.R
import com.aditya.espressorecord.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        intent?.let {
            binding.tvUserName.text = it.extras?.getString(KEY_USER_NAME)
        }

        binding.btnFirst.setOnClickListener {
            changeFragment(FirstFragment.newInstance(binding.tvUserName.text.toString()))
        }

        binding.btnSecond.setOnClickListener {
            changeFragment(SecondFragment.newInstance(binding.tvUserName.text.toString()))
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    companion object {
        private const val KEY_USER_NAME: String = "username"
        private const val KEY_PSSWORD: String = "password"

        fun generateIntent(context: Context, userName: String, password: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_USER_NAME, userName)
            intent.putExtra(KEY_PSSWORD, password)
            return intent;
        }
    }
}