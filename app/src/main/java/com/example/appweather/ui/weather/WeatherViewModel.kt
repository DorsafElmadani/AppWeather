package com.example.appweather.ui.weather
import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appweather.data.model.WeatherModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository : WeatherRepository) : ViewModel() {



    private val cities = listOf("Paris", "Rennes", "Nantes", "Bordeaux", "Lyon")

    private val _weatherData = MutableLiveData<List<WeatherModel>>()
    val weatherData: LiveData<List<WeatherModel>> get() = _weatherData

    private val _progressPercentage = MutableLiveData<Int>()
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> get() = _progress
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _error


    private val _loadingVisible = MutableLiveData<Boolean>()
    val loadingVisible: LiveData<Boolean> get() = _loadingVisible

    init {
        _loadingVisible.value = false
    }

    fun restartLoading() {
        _weatherData.value = emptyList()
        _progress.value = 0
        _loadingVisible.value = true
        displayMessages()
        fetchWeatherData()
    }
    private fun fetchWeatherData() {
        viewModelScope.launch {
            try {
                _error.postValue(false) // Set _error to false in the normal state

                val cityDelay = 10000L // 10 seconds
                val data = mutableListOf<WeatherModel>()

                cities.forEachIndexed { index, city ->
                    delay(cityDelay)
                    val weather = repository.getWeatherData(city)
                    data.add(weather)
                    val progress = ((index + 1) * 100) / cities.size
                    _progress.postValue(progress)
                }

                _weatherData.postValue(data)
                _progressPercentage.postValue(100)
                _loadingVisible.postValue(false)
            } catch (exception: Exception) {
                when (exception) {
                    is NetworkErrorException -> _message.postValue("Erreur de réseau lors du chargement des données.")
                    is IOException -> _message.postValue("Veuillez vérifier votre connectivité réseau.")
                    is SocketTimeoutException -> _message.postValue("Veuillez réessayer ultérieurement.")
                    else -> _message.postValue("Erreur lors du chargement des données.")
                }
                _error.postValue(true)
                _loadingVisible.postValue(false)
            }
        }
    }


    private fun displayMessages() {
        val messages = listOf(
            "Nous téléchargeons les données...",
            "C'est presque fini...",
            "Plus que quelques secondes avant d'avoir le résultat..."
        )

        val displayInterval = 6 * 1000 // 6 seconds in milliseconds
        val totalTime = 60 * 1000 // 60 seconds in milliseconds

        val timer = Timer()
        var elapsedTime = 0

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (_error.value==true) {
                    timer.cancel()
                } else if (elapsedTime >= totalTime) {
                    timer.cancel()
                } else {
                    val messageIndex = (elapsedTime / displayInterval) % messages.size
                    _message.postValue(messages[messageIndex])
                    elapsedTime += displayInterval
                }
            }
        }, 0, displayInterval.toLong())
    }
}
