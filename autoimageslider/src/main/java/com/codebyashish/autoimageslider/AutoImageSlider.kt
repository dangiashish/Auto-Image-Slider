package com.codebyashish.autoimageslider

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.codebyashish.autoimageslider.Adapter.ViewAdapter
import com.codebyashish.autoimageslider.Animation.BackgroundToForeground
import com.codebyashish.autoimageslider.Animation.CubeIn
import com.codebyashish.autoimageslider.Animation.CubeOut
import com.codebyashish.autoimageslider.Animation.DepthSlide
import com.codebyashish.autoimageslider.Animation.FidgetSpinner
import com.codebyashish.autoimageslider.Animation.FlipHorizontal
import com.codebyashish.autoimageslider.Animation.FlipVertical
import com.codebyashish.autoimageslider.Animation.ForegroundToBackground
import com.codebyashish.autoimageslider.Animation.Gate
import com.codebyashish.autoimageslider.Animation.RotateDown
import com.codebyashish.autoimageslider.Animation.RotateUp
import com.codebyashish.autoimageslider.Animation.Toss
import com.codebyashish.autoimageslider.Animation.ZoomIn
import com.codebyashish.autoimageslider.Animation.ZoomOut
import com.codebyashish.autoimageslider.Enums.ImageAnimationTypes
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Interfaces.ItemsListener
import com.codebyashish.autoimageslider.Models.ImageSlidesModel
import java.util.Timer
import java.util.TimerTask

class AutoImageSlider @JvmOverloads constructor(
    var mContext: Context,
    var attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(
    mContext, attributeSet, defStyleAttr
) {
    private val viewPager: ViewPager
    private val pagerDots: LinearLayout
    private var viewAdapter: ViewAdapter? = null
    private lateinit var dotsImages: Array<ImageView?>
    private var currentPage = 0
    private var imageCount = 0
    private var cornerRadius = 0
    private var period: Long = 0
    private var delay: Long = 0
    private var autoCycle = false
    private var selectedDot = 0
    private var unselectedDot = 0
    private var errorImage = 0
    private var placeholder = 0
    private var titleBackground = 0
    private val indicatorStyle: String? = null
    private var textAlign: String? = "LEFT"
    private var indicatorAlign: String? = "CENTER"
    private var swipeTimer = Timer()
    private val listener: ItemsListener? = null
    private val itemChangeListener: ItemsListener? = null
    private var touchListener: ItemsListener? = null
    private var itemClickListener: ItemsListener? = null
    private val dotsVisible: Boolean
    private var titleColor = resources.getColor(android.R.color.white)
    private var descriptionColor = resources.getColor(android.R.color.white)
    private val defaultBackground = R.drawable.text_background
    private val view: View
    var defStyleAttr = 0
    var defStyleRes = 0

    init {
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_slider_image, this, true)
        viewPager = findViewById(R.id.view_pager)
        pagerDots = findViewById(R.id.pager_dots)
        val typedArray = getContext().theme.obtainStyledAttributes(
            attributeSet, R.styleable.AutoImageSlider, defStyleAttr, defStyleRes
        )
        cornerRadius = typedArray.getInt(R.styleable.AutoImageSlider_ais_corner_radius, 1)
        period = typedArray.getInt(R.styleable.AutoImageSlider_ais_time_interval, 1000).toLong()
        delay = typedArray.getInt(R.styleable.AutoImageSlider_ais_slide_delay, 1000).toLong()
        autoCycle = typedArray.getBoolean(R.styleable.AutoImageSlider_ais_auto_sliding, false)
        placeholder = typedArray.getResourceId(
            R.styleable.AutoImageSlider_ais_placeholder,
            R.drawable.placeholder_default_loading
        )
        errorImage = typedArray.getResourceId(
            R.styleable.AutoImageSlider_ais_exception_image,
            R.drawable.placeholder_image_failed
        )
        selectedDot = typedArray.getResourceId(
            R.styleable.AutoImageSlider_ais_selected_indicator,
            R.drawable.indicator_selected_dash
        )
        unselectedDot = typedArray.getResourceId(
            R.styleable.AutoImageSlider_ais_unselected_indicator,
            R.drawable.indicator_unselected_dash
        )
        if (typedArray.getString(R.styleable.AutoImageSlider_ais_indicator_style) == resources.getString(
                R.string.style_dash
            )
        ) {
            selectedDot = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_selected_indicator,
                R.drawable.indicator_selected_dash
            )
            unselectedDot = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_unselected_indicator,
                R.drawable.indicator_unselected_dash
            )
        }
        if (typedArray.getString(R.styleable.AutoImageSlider_ais_indicator_style) == resources.getString(
                R.string.style_dot
            )
        ) {
            selectedDot = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_selected_indicator,
                R.drawable.indicator_selected_dot
            )
            unselectedDot = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_unselected_indicator,
                R.drawable.indicator_unselected_dot
            )
        }
        titleBackground = typedArray.getResourceId(
            R.styleable.AutoImageSlider_ais_title_background,
            defaultBackground
        )
        dotsVisible = typedArray.getBoolean(R.styleable.AutoImageSlider_ais_dots_visible, true)
        if (typedArray.getString(R.styleable.AutoImageSlider_ais_text_align) != null) {
            textAlign = typedArray.getString(R.styleable.AutoImageSlider_ais_text_align)
        }
        if (typedArray.getString(R.styleable.AutoImageSlider_ais_indicator_align) != null) {
            indicatorAlign = typedArray.getString(R.styleable.AutoImageSlider_ais_indicator_align)
        }
        if (typedArray.getString(R.styleable.AutoImageSlider_ais_title_color) != null) {
            titleColor = typedArray.getString(R.styleable.AutoImageSlider_ais_title_color)!!
                .toInt()
        }
        if (typedArray.getString(R.styleable.AutoImageSlider_ais_description_color) != null) {
            descriptionColor =
                typedArray.getString(R.styleable.AutoImageSlider_ais_description_color)!!
                    .toInt()
        }
    }

    fun setImageList(arrayList: ArrayList<ImageSlidesModel>) {
        viewAdapter = ViewAdapter(
            mContext,
            arrayList,
            cornerRadius,
            errorImage,
            placeholder,
            titleBackground,
            textAlign,
            titleColor,
            descriptionColor,
            listener
        )
        setAdapter(arrayList)
    }

    fun setImageList(arrayList: ArrayList<ImageSlidesModel>, scaleType: ImageScaleType?) {
        Log.w("HWLL", "setImageList: ${arrayList}" )

        viewAdapter = ViewAdapter(
            mContext,
            arrayList,
            cornerRadius,
            errorImage,
            placeholder,
            titleBackground,
            scaleType,
            textAlign,
            titleColor,
            descriptionColor,
            listener
        )
        setAdapter(arrayList)


    }

    private fun setAdapter(arrayList: ArrayList<ImageSlidesModel>) {
        viewPager.adapter = viewAdapter
        imageCount = arrayList.size
        if (arrayList.size != 0) {
            if (dotsVisible) {
                setupDots(arrayList.size)
            }
            if (autoCycle) {
                startSliding(period)
            }
        }
    }

    fun setDefaultAnimation() {
        viewPager.setPageTransformer(true, BackgroundToForeground())
    }

    fun startSlider() {}
    fun setSlideAnimation(animationType: ImageAnimationTypes?) {
        when (animationType) {
            ImageAnimationTypes.ZOOM_IN -> viewPager.setPageTransformer(true, ZoomIn())
            ImageAnimationTypes.ZOOM_OUT -> viewPager.setPageTransformer(true, ZoomOut())
            ImageAnimationTypes.CUBE_IN -> viewPager.setPageTransformer(true, CubeIn())
            ImageAnimationTypes.CUBE_OUT -> viewPager.setPageTransformer(true, CubeOut())
            ImageAnimationTypes.FLIP_HORIZONTAL -> viewPager.setPageTransformer(
                true,
                FlipHorizontal()
            )

            ImageAnimationTypes.FLIP_VERTICAL -> viewPager.setPageTransformer(true, FlipVertical())
            ImageAnimationTypes.ROTATE_UP -> viewPager.setPageTransformer(true, RotateUp())
            ImageAnimationTypes.ROTATE_DOWN -> viewPager.setPageTransformer(true, RotateDown())
            ImageAnimationTypes.FOREGROUND_TO_BACKGROUND -> viewPager.setPageTransformer(
                true,
                ForegroundToBackground()
            )

            ImageAnimationTypes.BACKGROUND_TO_FOREGROUND -> viewPager.setPageTransformer(
                true,
                BackgroundToForeground()
            )

            ImageAnimationTypes.TOSS -> viewPager.setPageTransformer(true, Toss())
            ImageAnimationTypes.GATE -> viewPager.setPageTransformer(true, Gate())
            ImageAnimationTypes.FIDGET_SPINNER -> viewPager.setPageTransformer(
                true,
                FidgetSpinner()
            )

            else -> viewPager.setPageTransformer(true, DepthSlide())
        }
    }

    private fun setupDots(size: Int) {
        pagerDots.gravity = getAlignment(indicatorAlign)
        pagerDots.removeAllViews()
        dotsImages = arrayOfNulls(size)
        Log.d("TAG", "" + pagerDots.gravity)
        for (i in 0 until size) {
            dotsImages[i] = ImageView(mContext)
            dotsImages[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    mContext, unselectedDot
                )
            )
            val params = LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10, 0, 10, 0)
            pagerDots.addView(dotsImages[i], params)
        }
        dotsImages[0]!!
            .setImageDrawable(ContextCompat.getDrawable(mContext, selectedDot))
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currentPage = position
                for (dotImage in dotsImages) {
                    dotImage!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            mContext, unselectedDot
                        )
                    )
                }
                dotsImages[position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        mContext, selectedDot
                    )
                )
                itemChangeListener?.onItemChanged(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun startSliding(changeableInterval: Long) {
        stopSliding()
        scheduleTimer(changeableInterval)
    }

    private fun stopSliding() {
        swipeTimer.cancel()
        swipeTimer.purge()
    }

    private fun scheduleTimer(period: Long) {
        setViewPageScroller(PageScroller(mContext))
        val handler = Handler()
        val update = Runnable {
            if (currentPage == imageCount) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }
        swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, delay, period)
    }

    private fun setViewPageScroller(pageScroller: PageScroller?) {
        try {
            val mScroller = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller.isAccessible = true
            mScroller[this] = pageScroller
        } catch (e: NoSuchFieldException) {
            // Handle NoSuchFieldException
        } catch (e: IllegalArgumentException) {
            // Handle IllegalArgumentException
        } catch (e: IllegalAccessException) {
            // Handle IllegalAccessException
        }
    }

    private fun getAlignment(textAlign: String?): Int {
        return if (textAlign == "RIGHT") {
            Gravity.END
        } else if (textAlign == "LEFT") {
            Gravity.START
        } else {
            Gravity.CENTER
        }
    }

    fun onItemClickListener(listener: ItemsListener?) {
        itemClickListener = listener

            viewAdapter?.setItemClickListener(listener)


    }

    fun onItemChangeListener(listener: ItemsListener?) {
        viewAdapter?.setItemClickListener(listener)
    }

    fun onItemTouchListener(listener: ItemsListener?) {
        touchListener = listener
        viewAdapter?.setItemTouchListener(touchListener)
    }

    fun onItemDoubleTapListener(listener: ItemsListener?) {
        viewAdapter?.setItemDoubleTapListener(listener)
    }
}
