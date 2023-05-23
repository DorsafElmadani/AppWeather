package com.example.appweather.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import com.airbnb.lottie.LottieDrawable
import com.example.appweather.R
import com.example.appweather.databinding.ActivityMainBinding
import com.example.appweather.ui.weather.WeatherActivity


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setupAnim()

        binding.startButton.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun setupAnim() {
        binding.animationView.setAnimation(R.raw.appweather)
        binding.animationView.repeatCount = LottieDrawable.INFINITE
        binding.animationView.playAnimation()
    }

}
