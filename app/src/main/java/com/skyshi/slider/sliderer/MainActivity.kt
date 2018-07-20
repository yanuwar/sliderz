package com.skyshi.slider.sliderer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val listData: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        listData.add("cihuy1")
//        listData.add("cihuy2")
//        listData.add("cihuy3")
//        listData.add("cihuy4")
//        view_pager.adapter = ExampleAdapter(listData)
//        val tes = indicator
//        indicator.setupWithViewPager(view_pager, true)
    }
}
