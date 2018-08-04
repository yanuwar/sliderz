package com.skyshi.slider.sliderz.base

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater

abstract class SliderAdapter<T, VH: SliderAdapter.ViewHolder> (private var data: MutableList<T>): PagerAdapter(){
    override fun isViewFromObject(view: View, `object`: Any): Boolean{ return view == (`object` as VH).itemView }

    abstract fun getItemResourceLayout(): Int

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    fun getView(parent: ViewGroup): View {
        val inflater = parent.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(getItemResourceLayout(), null)
    }

    fun getData(): List<T> = data

    override fun getCount(): Int = data.size

    fun add(item: T) = data.add(item)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView((`object` as VH).itemView)
    }

    abstract class ViewHolder(internal val itemView: View)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val viewHolder = onCreateViewHolder(container)
        container.addView(viewHolder.itemView)
        onBindViewHolder(viewHolder, position)

        return viewHolder
    }

    abstract fun onCreateViewHolder(parent: ViewGroup): VH

    abstract fun onBindViewHolder(viewHolder: VH, position: Int)

}