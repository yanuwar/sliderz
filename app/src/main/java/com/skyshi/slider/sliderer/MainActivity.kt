package com.skyshi.slider.sliderer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val listData: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listData.add("cihuy1")
        listData.add("cihuy2")
        listData.add("cihuy3")
        listData.add("cihuy5")
        val adapter = ExampleAdapter(listData)
        sliderer.setAdapter(adapter)
        sliderer.setVisibilityBottomIndicator(false)
        sliderer.isAutoSlide(true)
        sliderer.setVisibilityNextBeforeIndicator(true)
        sliderer.setVisibilityInsideIndicator(true)
    }
}
