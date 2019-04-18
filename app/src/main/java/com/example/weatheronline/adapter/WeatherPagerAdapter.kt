package com.example.weatheronline.adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class WeatherPagerAdapter(manage: FragmentManager,val mfragmentList : ArrayList<Fragment>) : FragmentPagerAdapter(manage)  {

    override fun getItem(p0: Int): Fragment = mfragmentList[p0]

    override fun getCount(): Int = mfragmentList.size
}