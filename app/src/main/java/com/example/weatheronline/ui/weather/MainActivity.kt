package com.example.weatheronline.ui.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.weatheronline.R
import com.example.weatheronline.adapter.WeatherPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapterWeather: WeatherPagerAdapter
    val listFragment = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitleActionBar(toolbar,"")

        toolbar.setNavigationOnClickListener {
         Toast.makeText(this,"clicked !",Toast.LENGTH_SHORT).show()
        }

        adapterWeather = WeatherPagerAdapter(supportFragmentManager, listFragment)

        for (i in 0..4){
            listFragment.add(WeatherFragment())
        }

        vpMain.adapter = adapterWeather
        dots_indicator.setViewPager(vpMain)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
          R.id.menuToolbar -> {
              Toast.makeText(this,"Menu",Toast.LENGTH_SHORT).show()
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
