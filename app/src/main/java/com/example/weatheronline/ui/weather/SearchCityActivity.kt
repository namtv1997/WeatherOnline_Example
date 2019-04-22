package com.example.weatheronline.ui.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.weatheronline.R
import kotlinx.android.synthetic.main.activity_search_city.*

class SearchCityActivity : AppCompatActivity() {

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s!!.isNotEmpty()){
                imgEmpty.visibility = View.GONE
                tvEmpty.visibility = View.GONE
            }else{
                imgEmpty.visibility = View.VISIBLE
                tvEmpty.visibility = View.VISIBLE
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_city)

        imgBack.setOnClickListener {
            finish()
        }
      edtSearchCity.addTextChangedListener(textWatcher)

    }
}
