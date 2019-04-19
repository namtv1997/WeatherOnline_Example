package com.example.weatheronline.ui.weather


import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.weatheronline.R
import com.example.weatheronline.adapter.WeatherPagerAdapter
import com.example.weatheronline.common.Common
import com.example.weatheronline.model.ListWeatherInfor
import com.example.weatheronline.viewmodel.WeatherViewmodel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var adapterWeather: WeatherPagerAdapter
    private lateinit var weatherViewModel: WeatherViewmodel

    var listData = ArrayList<ListWeatherInfor>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitleActionBar(toolbar, "")

        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "clicked !", Toast.LENGTH_SHORT).show()
        }


        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewmodel::class.java).apply {
            weather.observe(this@MainActivity, android.arch.lifecycle.Observer {
                if (it != null) {

                    listData = it.list as ArrayList<ListWeatherInfor>

                    adapterWeather = WeatherPagerAdapter(supportFragmentManager, listData, it)
                    vpMain.adapter = adapterWeather
                    dots_indicator.setViewPager(vpMain)

                }
            })
        }

        weatherViewModel.getDataWeather(34.9667, 138.9333, Common.API_Key)

        val uploadWorkRequest = PeriodicWorkRequest
            .Builder(TimerWorker::class.java,3,TimeUnit.HOURS)
            .build()

        WorkManager.getInstance().enqueue(uploadWorkRequest)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuToolbar -> {
                Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTitleActionBar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.let {
            tvTitleToolbar.text = title
            it.title = ""
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_search)
        }
    }

}
