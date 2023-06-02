package com.codebyashish.autoimageslider.Animation

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer
import kotlin.math.abs


class ZoomIn: PageTransformer {

    override fun transformPage(view: View, positon: Float) {
        val scale: Float = if (positon < 0) positon + 1f else abs(1f - positon)
        view.scaleX = scale
        view.scaleY = scale
        view.pivotX = view.width * 0.5f
        view.pivotY = view.height * 0.5f
        view.alpha = if (positon < -1f || positon > 1f) 0f else 1f - (scale - 1f)
    }

}