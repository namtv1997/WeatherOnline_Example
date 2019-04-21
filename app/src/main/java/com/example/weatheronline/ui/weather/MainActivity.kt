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
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var adapterWeather: WeatherPagerAdapter
    private lateinit var weatherViewModel: WeatherViewmodel

    private var listData = ArrayList<ArrayList<ListWeatherInfor>>()
    private var listDataDayOne = ArrayList<ListWeatherInfor>()
    private var listDataDayTwo = ArrayList<ListWeatherInfor>()
    private var listDataDayThree = ArrayList<ListWeatherInfor>()
    private var listDataDayFour = ArrayList<ListWeatherInfor>()
    private var listDataDayFive = ArrayList<ListWeatherInfor>()
    private var listDataDaySix = ArrayList<ListWeatherInfor>()


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

                    for (i in 0..2)
                            listDataDayOne.add(it.list!![i])

                    for (i in 3..10)

                        listDataDayTwo.add(it.list!![i])

                    for (i in 11..18)

                        listDataDayThree.add(it.list!![i])

                    for (i in 19..26)

                        listDataDayFour.add(it.list!![i])

                    for (i in 27..34)

                        listDataDayFive.add(it.list!![i])

                    for (i in 35..39)

                        listDataDaySix.add(it.list!![i])

                    listData.add(listDataDayOne)
                    listData.add(listDataDayTwo)
                    listData.add(listDataDayThree)
                    listData.add(listDataDayFour)
                    listData.add(listDataDayFive)
                    listData.add(listDataDaySix)

                    adapterWeather = WeatherPagerAdapter(supportFragmentManager, listData, it)
                    vpMain.adapter = adapterWeather
                    dots_indicator.setViewPager(vpMain)

                }
            })
        }

        weatherViewModel.getDataWeather(34.9667, 138.9333, Common.API_Key)

        val uploadWorkRequest = PeriodicWorkRequest
            .Builder(TimerWorker::class.java, 3, TimeUnit.HOURS)
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

//    fun isToday(dt: String): Boolean {
//        try {
//            // our weather date returned from api
//            val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//            val weatherdate = format.parse(dt)
//
//            // today with time removed
//            val todayWithNoTime = format.parse(format.format(weatherdate))
//
//            // return true if equal
//            return weatherdate.compareTo(todayWithNoTime) == 0
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//
//        return false
//    }

}
