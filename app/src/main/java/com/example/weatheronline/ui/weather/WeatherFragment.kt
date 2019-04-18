package com.example.weatheronline.ui.weather

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.WeatherResult
import com.example.weatheronline.viewmodel.WeatherViewmodel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*
import java.text.SimpleDateFormat

class WeatherFragment : Fragment() {
    private lateinit var weatherViewModel: WeatherViewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.example.weatheronline.R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pbHumidity.max = 100
        pbWind.max = 13

        weatherViewModel = activity?.run {
            ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
                weather.observe(this@WeatherFragment, Observer { result ->
                    result?.let {
                        showDataWeather(it)

                    }
                })
            }
        } ?: throw Exception("Invalid Activity")


        weatherViewModel.getDataWeather(34.9667, 138.9333, Common.API_Key)


    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun showDataWeather(weather: WeatherResult) {
        val listWeather = weather.list!![0].let { it ->
            val kelvin = it.main?.temp
            val celsius = kelvin?.minus(273.15)
            val result = Math.round(celsius!!).toString()

            val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val dateInString = sdf.parse(it.dtTxt)
            sdf.applyPattern("hh:mm a - dd MMM yy")

            tvCity.text = weather.city.name
            tvTemperature.text = "$result\u00B0"
            tvTime.text = sdf.format(dateInString)
            tvsttWeather.text = it.weather!![0].main
            tv13Wind.text = it.wind?.speed.toString()
            pbWind.progress = it.wind?.speed!!.toInt()
            tv88Humidity.text = it.main.humidity.toString()
            pbHumidity.progress = it.main.humidity!!.toInt()
        }



        when {
            tvsttWeather.text == "Clouds" -> {
                imageView.setImageResource(com.example.weatheronline.R.drawable.bg_cloudy)
                imgWeather.setImageResource(com.example.weatheronline.R.drawable.ic_cloudy)
            }
            tvsttWeather.text == "Rain" -> {
                imageView.setImageResource(com.example.weatheronline.R.drawable.bg_rain)
                imgWeather.setImageResource(com.example.weatheronline.R.drawable.ic_rain)
            }
            tvsttWeather.text == "Clear" -> {
                imageView.setImageResource(com.example.weatheronline.R.drawable.bg_snow)
                imgWeather.setImageResource(com.example.weatheronline.R.drawable.ic_cloudy)
            }
        }


    }
}
