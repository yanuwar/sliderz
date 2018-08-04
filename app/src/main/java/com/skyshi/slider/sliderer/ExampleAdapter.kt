package com.skyshi.slider.sliderer

import android.view.ViewGroup
import android.view.View
import com.skyshi.slider.sliderz.base.SliderAdapter
import kotlinx.android.synthetic.main.item_tes.view.*

class ExampleAdapter(private val listData: MutableList<String>) : SliderAdapter<String, ExampleAdapter.FooViewHolder>(listData){
    override fun onCreateViewHolder(parent: ViewGroup): FooViewHolder {
        return FooViewHolder(getView(parent))
    }

    override fun onBindViewHolder(viewHolder: FooViewHolder, position: Int) {
        viewHolder.show(listData[position])
    }

    override fun getItemResourceLayout(): Int = R.layout.item_tes

    class FooViewHolder(val v: View)
        : SliderAdapter.ViewHolder(v) {

        fun show(value: String) {
            v.tv_testo.text=value
        }
    }
}