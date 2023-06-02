package com.codebyashish.autoimageslider.Animation

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer


class RotateDown: PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val width = view.width
        val rotation = -15f * position

        view.pivotX = width * 0.5f
        view.pivotY = 0f
        view.translationX = 0f
        view.rotation = rotation
    }

}