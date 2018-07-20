package com.skyshi.slider.sliderz.ui

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.LinearLayout
import com.skyshi.slider.sliderz.R
import com.skyshi.slider.sliderz.adapter.ExampleAdapter
import android.support.design.widget.TabLayout
import android.view.LayoutInflater


val listData: MutableList<String> = arrayListOf()

class Sliderz(context: Context?, attributeSet: AttributeSet?) : LinearLayout(context, attributeSet) {
    init {
        listData.add("cihuy1")
        listData.add("cihuy2")
        listData.add("cihuy3")
        listData.add("cihuy4")
        //Inflate view
        val view = LayoutInflater.from(getContext()).inflate(R.layout.slide_parent, this, true)
        val view_pager = view.findViewById<ViewPager>(R.id.view_pager)
        view_pager.adapter = ExampleAdapter(listData)
        val indicator = view.findViewById<TabLayout>(R.id.indicator)
        indicator.setupWithViewPager(view_pager, true)
    }
}