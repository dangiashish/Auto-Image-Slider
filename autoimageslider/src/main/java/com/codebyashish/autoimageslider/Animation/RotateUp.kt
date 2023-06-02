package com.codebyashish.autoimageslider.Animation

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer

class RotateUp: PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val width = view.width
        val height = view.height
        val rotation = -15f * position * -1.25f

        view.pivotX = width * 0.5f
        view.pivotY = height.toFloat()
        view.rotation = rotation
    }

}