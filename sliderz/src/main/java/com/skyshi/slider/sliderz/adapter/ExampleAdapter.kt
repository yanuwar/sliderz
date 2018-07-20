package com.skyshi.slider.sliderz.adapter

import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.widget.TextView
import com.skyshi.slider.sliderz.R
import com.skyshi.slider.sliderz.base.SliderAdapter

class ExampleAdapter(val listData: MutableList<String>) : SliderAdapter<String>(listData) {
    override fun getItemResourceLayout(): Int = R.layout.item_tes

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = getView(container)

        val textView = view.findViewById<TextView>(R.id.tv_testo)

        textView.text = listData[position]

        val viewPager = container as ViewPager
        viewPager.addView(view, 0)

        return view
    }
}