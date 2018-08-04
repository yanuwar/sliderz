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
import android.widget.ImageView
import java.util.*


lateinit var view_pager: ViewPager
lateinit var indicator: TabLayout
lateinit var indicatorInside: TabLayout
lateinit var view: View
lateinit var leftArrow: ImageView
lateinit var rightArrow: ImageView
lateinit var contextSlider: Context
lateinit var adapterSlider: PagerAdapter
var delay: Long = 1000

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
        indicatorInside = view.findViewById(R.id.indicator_inside)
        leftArrow = view.findViewById(R.id.left_arrow)
        rightArrow = view.findViewById(R.id.right_arrow)

        indicator.setupWithViewPager(view_pager, false)
        indicatorInside.setupWithViewPager(view_pager, false)
        leftArrow.setOnClickListener {
            if (view_pager.currentItem-1 > -1) view_pager.currentItem = view_pager.currentItem-1 else view_pager.currentItem = adapterSlider.count-1
        }
        rightArrow.setOnClickListener {
            if (view_pager.currentItem+1 < adapterSlider.count) view_pager.currentItem = view_pager.currentItem+1 else view_pager.currentItem = 0
        }

        initViewStyle(a)
    }

    fun setAdapter(adapter: PagerAdapter) {
        adapterSlider = adapter
        view_pager.adapter = adapter
    }

    private fun initViewStyle(a: TypedArray?) {
        setVisibilityBottomIndicator(a!!.getBoolean(R.styleable.Sliderz_bottom_indicator_visibility, false))
        setVisibilityInsideIndicator(a.getBoolean(R.styleable.Sliderz_inside_indicator_visibility, false))
        setVisibilityNextBeforeIndicator(a.getBoolean(R.styleable.Sliderz_next_before_indicator_visibility, false))
        delay = a.getInteger(R.styleable.Sliderz_delay, delay.toInt()).toLong()
        isAutoSlide (a.getBoolean(R.styleable.Sliderz_is_autoslide, false))
    }

    fun setVisibilityBottomIndicator(show: Boolean) {
        if (show) indicator.visibility = View.VISIBLE else indicator.visibility = View.GONE
    }

    fun setVisibilityInsideIndicator(show: Boolean) {
        if (show) indicatorInside.visibility = View.VISIBLE else indicatorInside.visibility = View.GONE
    }

    fun setVisibilityNextBeforeIndicator(show: Boolean) {
        if (show) leftArrow.visibility = View.VISIBLE else leftArrow.visibility = View.GONE
        if (show) rightArrow.visibility = View.VISIBLE else rightArrow.visibility = View.GONE
    }

    fun isAutoSlide (auto: Boolean) {
        if (auto) {
            val timer = Timer()
            timer.scheduleAtFixedRate(SliderTimer(), delay, delay)
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