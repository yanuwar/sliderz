package com.skyshi.slider.sliderz.ui

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.LinearLayout
import com.skyshi.slider.sliderz.R
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View

@SuppressLint("StaticFieldLeak")
lateinit var view_pager: ViewPager
@SuppressLint("StaticFieldLeak")
lateinit var indicator: TabLayout
@SuppressLint("StaticFieldLeak")
lateinit var view: View

class Sliderz(context: Context?, attributeSet: AttributeSet?) : LinearLayout(context, attributeSet) {
    init {
        //Inflate view
        view = LayoutInflater.from(getContext()).inflate(R.layout.slide_parent, this, true)
        view_pager = view.findViewById(R.id.view_pager)
        indicator = view.findViewById(R.id.indicator_bottom)
        indicator.setupWithViewPager(view_pager, false)
    }

    fun setAdapter(adapter: PagerAdapter) {
        view_pager.adapter = adapter
    }
}