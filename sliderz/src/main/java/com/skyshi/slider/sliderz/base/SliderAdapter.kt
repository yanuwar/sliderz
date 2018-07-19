package com.skyshi.slider.sliderz.base

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.support.v4.view.ViewPager
import android.view.LayoutInflater


abstract class SliderAdapter<T> (private var data: MutableList<T>): PagerAdapter(){
    override fun isViewFromObject(view: View, `object`: Any): Boolean{ return view == `object` }

    abstract fun getItemResourceLayout(): Int

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    fun getView(parent: ViewGroup): View = LayoutInflater.from(parent.context).inflate(getItemResourceLayout(), parent, false)

    fun getData(): List<T> = data

    override fun getCount(): Int = data.size

    fun add(item: T) = data.add(item)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }
}