package com.skyshi.slider.sliderz.ui

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.LinearLayout
import com.skyshi.slider.sliderz.R
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.content.res.TypedArray
import android.os.AsyncTask
import java.util.*


lateinit var view_pager: ViewPager
lateinit var indicator: TabLayout
lateinit var view: View
lateinit var contextSlider: Context
lateinit var adapterSlider: PagerAdapter

class Sliderz(context: Context?, attributeSet: AttributeSet?) : LinearLayout(context, attributeSet) {
    init {
        contextSlider = context!!
        val a = context.theme?.obtainStyledAttributes(
                attributeSet,
                R.styleable.Sliderz,
                0, 0)
        view = LayoutInflater.from(getContext()).inflate(R.layout.slide_parent, this, true)
        view_pager = view.findViewById(R.id.view_pager)
        indicator = view.findViewById(R.id.indicator_bottom)
        indicator.setupWithViewPager(view_pager, false)

        initViewStyle(a)
    }

    fun setAdapter(adapter: PagerAdapter) {
        adapterSlider = adapter
        view_pager.adapter = adapter
    }

    private fun initViewStyle(a: TypedArray?) {
        setVisibilityBottomIndicator(a!!.getBoolean(R.styleable.Sliderz_bottom_indicator_visibility, false))
        isAutoSlide (true)
    }

    fun setVisibilityBottomIndicator(show: Boolean) {
        if (show) indicator.visibility = View.VISIBLE else indicator.visibility = View.INVISIBLE
    }

    fun isAutoSlide (auto: Boolean) {
        if (auto) {
            val timer = Timer()
            timer.scheduleAtFixedRate(SliderTimer(), 4000, 6000)
        }
    }

    private inner class SliderTimer : TimerTask() {
        override fun run() {
            RunningTask().execute()
        }
    }

    private inner class RunningTask() : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (view_pager.currentItem < adapterSlider.count - 1) {
                view_pager.currentItem = view_pager.currentItem + 1
            } else {
                view_pager.currentItem = 0
            }
        }

    }
}