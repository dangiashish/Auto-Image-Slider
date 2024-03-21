package com.codebyashish.autoimageslider;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class PageScroller extends Scroller {
    int fixedDuration = 1000;
    public PageScroller(Context context) {
        super(context);
    }

    public PageScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public PageScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {

        super.startScroll(startX, startY, dx, dy, fixedDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, fixedDuration);
    }

}
