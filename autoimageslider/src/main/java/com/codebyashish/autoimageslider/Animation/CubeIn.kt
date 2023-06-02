package com.codebyashish.autoimageslider.Animation

import android.view.View
import androidx.viewpager.widget.ViewPager.PageTransformer

class CubeIn: PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.pivotX = if (position > 0) 0f else view.width.toFloat()
        view.pivotY = 0f
        view.rotationY = -90f * position
    }

}