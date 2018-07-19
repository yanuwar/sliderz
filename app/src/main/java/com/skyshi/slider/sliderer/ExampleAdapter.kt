package com.skyshi.slider.sliderer

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_tes.view.*
import android.support.v4.view.ViewPager
import com.skyshi.slider.sliderz.base.SliderAdapter

class ExampleAdapter(val listData: MutableList<String>) : SliderAdapter<String>(listData) {
    override fun getItemResourceLayout(): Int = R.layout.item_tes

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = getView(container)

        view.tv_testo.text = listData[position]

        val viewPager = container as ViewPager
        viewPager.addView(view, 0)

        return view
    }
}