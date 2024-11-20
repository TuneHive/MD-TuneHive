package com.example.tunehive.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.tunehive.R
import com.example.tunehive.databinding.ActivityAuthBinding
import com.example.tunehive.databinding.ActivityMainBinding
import com.example.tunehive.ui.auth.login.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.log

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val loginFragment = LoginFragment()
        val fragment = fragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)

        if(fragment !is LoginFragment){
            Log.d("AuthActivity","Fragment Name: "+ LoginFragment::class.java.simpleName)

            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container,loginFragment,LoginFragment::class.java.simpleName)
                .commit()
        }

    }
}