package com.skyshi.slider.sliderz.ui

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.LinearLayout
import com.skyshi.slider.sliderz.R
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.content.res.TypedArray
import android.os.AsyncTask
import android.support.v4.content.res.ResourcesCompat
import android.widget.ImageView
import java.util.*

class Sliderz(context: Context?, attributeSet: AttributeSet?) : LinearLayout(context, attributeSet) {
    val INDICATOR_BOTTOM = 1
    val INDICATOR_INSIDE = 2
    val INDICATOR_DEFAULT = 0

    var delay: Long = 1000
    var viewPager: ViewPager
    var view: View
    private var indicatorBottomVisibility = false
    private var indicatorInsideVisibility = false
    lateinit var adapterSlider: PagerAdapter

    init {
        val a = context!!.theme?.obtainStyledAttributes(
                attributeSet,
                R.styleable.Sliderz,
                0, 0)
        view = LayoutInflater.from(getContext()).inflate(R.layout.slide_parent, this, true)
        viewPager = view.findViewById(R.id.view_pager)

        initViewStyle(a)
    }

    fun setAdapter(adapter: PagerAdapter) {
        adapterSlider = adapter
        viewPager.adapter = adapter
        createBottomIndicator(adapterSlider.count-1)
    }

    //adapter with own indicator image
    fun setAdapter(adapter: PagerAdapter, indicatorImageView: ImageView) {
        adapterSlider = adapter
        viewPager.adapter = adapter
        createBottomIndicator(adapterSlider.count-1, indicatorImageView)
    }

    private fun initViewStyle(a: TypedArray?) {
        setVisibilityIndicator(a!!.getInt(R.styleable.Sliderz_indicator_visibility, INDICATOR_DEFAULT))
        setVisibilityNextBeforeIndicator(a.getBoolean(R.styleable.Sliderz_next_before_indicator_visibility, false))
        delay = a.getInteger(R.styleable.Sliderz_delay, delay.toInt()).toLong()
        isAutoSlide (a.getBoolean(R.styleable.Sliderz_is_autoslide, false))
    }

    fun setVisibilityIndicator(value: Int) {
        if (value == INDICATOR_BOTTOM) {
            indicatorBottomVisibility = true
        } else if (value == INDICATOR_INSIDE) {
            indicatorInsideVisibility = true
        }
    }

    fun setVisibilityNextBeforeIndicator(show: Boolean) {
        if (show) {
            val leftArrow: ImageView = view.findViewById(R.id.left_arrow)
            leftArrow.visibility = View.VISIBLE
            val rightArrow: ImageView = view.findViewById(R.id.right_arrow)
            rightArrow.visibility = View.VISIBLE

            leftArrow.setOnClickListener {
                if (viewPager.currentItem-1 > -1) viewPager.currentItem = viewPager.currentItem-1 else viewPager.currentItem = adapterSlider.count-1
            }
            rightArrow.setOnClickListener {
                if (viewPager.currentItem+1 < adapterSlider.count) viewPager.currentItem = viewPager.currentItem+1 else viewPager.currentItem = 0
            }
        }
    }

    fun isAutoSlide (auto: Boolean) {
        if (auto) {
            val timer = Timer()
            timer.scheduleAtFixedRate(SliderTimer(), delay, delay)
        }
    }

    private fun createBottomIndicator(count: Int) {
        if (indicatorBottomVisibility) {
            val indicator: LinearLayout = view.findViewById(R.id.indicator_bottom)
            indicator.visibility = View.VISIBLE
            val arrImageView = arrayOfNulls<ImageView>(count+1)
            val lp = LayoutParams(resources.getDimension(R.dimen.width_indicator).toInt(), resources.getDimension(R.dimen.height_indicator).toInt())
            for (i in count downTo 0) {
                arrImageView[i] = ImageView(context)
                arrImageView[i]?.layoutParams = lp
                arrImageView[i]?.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.indicator_selector, null))
                indicator.addView(arrImageView[i])
                arrImageView[i]?.setOnClickListener {
                    viewPager.currentItem = i
                }
            }
            setOnSlideListener(arrImageView, count)
        }

        if (indicatorInsideVisibility) {
            val indicator: LinearLayout = view.findViewById(R.id.indicator_inside)
            indicator.visibility = View.VISIBLE
            val arrImageView = arrayOfNulls<ImageView>(count+1)
            val lp = LayoutParams(resources.getDimension(R.dimen.width_indicator).toInt(), resources.getDimension(R.dimen.height_indicator).toInt())
            for (i in count downTo 0) {
                arrImageView[i] = ImageView(context)
                arrImageView[i]?.layoutParams = lp
                arrImageView[i]?.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.indicator_selector, null))
                indicator.addView(arrImageView[i])
                arrImageView[i]?.setOnClickListener {
                    viewPager.currentItem = i
                }
            }
            setOnSlideListener(arrImageView, count)
        }
    }

    private fun createBottomIndicator(count: Int, imageIndicator: ImageView) {
        val indicator: LinearLayout = view.findViewById(R.id.indicator_bottom)
        val arrImageView = arrayOfNulls<ImageView>(count+1)
        for (i in count downTo 0) {
            indicator.addView(imageIndicator)
            imageIndicator.setOnClickListener {
                viewPager.currentItem = i
            }
        }
        setOnSlideListener(arrImageView, count)
    }

    private fun setOnSlideListener (arrImageView: Array<ImageView?>, count: Int) {
        var lastPosition = count
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (lastPosition != position) {
                    changeIndicator(arrImageView[lastPosition]!!, true)
                    changeIndicator(arrImageView[position]!!, false)
                    lastPosition = position
                }
            }

            override fun onPageSelected(position: Int) { }

        })
    }

    private fun changeIndicator (imageView: ImageView, state: Boolean) {
        imageView.isEnabled = state
    }

    private inner class SliderTimer : TimerTask() {
        override fun run() {
            object : AsyncTask<String, String, String>() {
                override fun doInBackground(vararg params: String?): String {
                    return ""
                }
                override fun onPostExecute(result: String?) {
                    super.onPostExecute(result)
                    if (viewPager.currentItem < adapterSlider.count - 1) {
                        viewPager.currentItem = viewPager.currentItem + 1
                    } else {
                        viewPager.currentItem = 0
                    }
                }
            }.execute()
        }
    }
}