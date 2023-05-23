package com.example.appweather.ui.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.example.appweather.R

import com.example.appweather.databinding.ActivityWeatherBinding
import com.example.appweather.databinding.TableRowItemBinding
import com.example.appweather.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val viewModel : WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.restartLoading()
        setupAnim()
        viewModel.weatherData.observe(this) { cities ->
            binding.tableLayout.removeAllViews()
            val inflater = layoutInflater

            cities.forEach { city ->
                val rowBinding: TableRowItemBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.table_row_item,
                    binding.tableLayout,
                    true
                )
                rowBinding.city = city
                val imageUrl = "http://openweathermap.org/img/wn/${city.weather[0].icon}@2x.png"

                // Load image using Glide
                Glide.with(this)
                    .load(imageUrl)
                    .into(rowBinding.imageView)
            }
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun setupAnim() {

        binding.weatherAnimationView.setAnimation(R.raw.error)
        binding.weatherAnimationView.repeatCount = LottieDrawable.INFINITE
        binding.weatherAnimationView.playAnimation()
    }
}