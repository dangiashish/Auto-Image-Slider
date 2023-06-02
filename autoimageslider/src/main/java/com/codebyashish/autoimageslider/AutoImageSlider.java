package com.codebyashish.autoimageslider;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.codebyashish.autoimageslider.Adapter.ViewAdapter;
import com.codebyashish.autoimageslider.Animation.BackgroundToForeground;
import com.codebyashish.autoimageslider.Animation.CubeIn;
import com.codebyashish.autoimageslider.Animation.CubeOut;
import com.codebyashish.autoimageslider.Animation.DepthSlide;
import com.codebyashish.autoimageslider.Animation.FidgetSpinner;
import com.codebyashish.autoimageslider.Animation.FlipHorizontal;
import com.codebyashish.autoimageslider.Animation.FlipVertical;
import com.codebyashish.autoimageslider.Animation.ForegroundToBackground;
import com.codebyashish.autoimageslider.Animation.Gate;
import com.codebyashish.autoimageslider.Animation.RotateDown;
import com.codebyashish.autoimageslider.Animation.RotateUp;
import com.codebyashish.autoimageslider.Animation.Toss;
import com.codebyashish.autoimageslider.Animation.ZoomIn;
import com.codebyashish.autoimageslider.Animation.ZoomOut;
import com.codebyashish.autoimageslider.Enums.ImageAnimationTypes;
import com.codebyashish.autoimageslider.Enums.ImageScaleType;
import com.codebyashish.autoimageslider.Interfaces.ItemsListener;
import com.codebyashish.autoimageslider.Models.ImageSlidesModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class AutoImageSlider  extends RelativeLayout{
    private ViewPager viewPager;
    private LinearLayout pagerDots;
    private ViewAdapter viewAdapter;
    private ImageView[] dotsImages;
    private int currentPage = 0;
    private int imageCount  = 0;
    private int cornerRadius = 0;
    private long period = 0;
    private long delay = 0;
    private boolean autoCycle = false;
    private int selectedDot = 0;
    private int unselectedDot = 0;
    private int errorImage = 0;
    private int placeholder = 0;
    private int titleBackground = 0;
    private String indicatorStyle;
    private String textAlign = "LEFT";
    private String indicatorAlign = "CENTER";
    private Timer swipeTimer = new Timer();
    private ItemsListener listener;
    private ItemsListener itemChangeListener = null;
    private ItemsListener touchListener = null;
    private ItemsListener itemClickListener = null;
    private boolean noDots = false;
    private int titleColor = getResources().getColor(android.R.color.white);
    private int descriptionColor = getResources().getColor(android.R.color.white);

    private View view;
    AttributeSet attributeSet;
    int defStyleAttr = 0;
    int defStyleRes = 0;
    Context context;

    public AutoImageSlider(Context context) {
        this(context, null);

    }

    public AutoImageSlider(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoImageSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attributeSet = attrs;
        this.context = context;

        view =  LayoutInflater.from(getContext()).inflate(R.layout.layout_slider_image, this, true);
        viewPager = findViewById(R.id.view_pager);
        pagerDots = findViewById(R.id.pager_dots);

        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attributeSet, R.styleable.AutoImageSlider, defStyleAttr, defStyleRes);

        cornerRadius = typedArray.getInt(R.styleable.AutoImageSlider_ais_corner_radius, 1);
        period = typedArray.getInt(R.styleable.AutoImageSlider_ais_time_interval, 1000);
        delay = typedArray.getInt(R.styleable.AutoImageSlider_ais_slide_delay, 1000);
        autoCycle = typedArray.getBoolean(R.styleable.AutoImageSlider_ais_auto_sliding, false);
        placeholder = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_placeholder,
                R.drawable.placeholder_default_loading
        );
        errorImage = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_exception_image,
                R.drawable.placeholder_image_failed
        );
        selectedDot = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_selected_indicator,
                R.drawable.indicator_selected_dash
        );
        unselectedDot = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_unselected_indicator,
                R.drawable.indicator_unselected_dash
        );

        if (Objects.equals(typedArray.getString(R.styleable.AutoImageSlider_ais_indicator_style), getResources().getString(R.string.style_dash))){
            selectedDot = typedArray.getResourceId(
                    R.styleable.AutoImageSlider_ais_selected_indicator,
                    R.drawable.indicator_selected_dash
            );
            unselectedDot = typedArray.getResourceId(
                    R.styleable.AutoImageSlider_ais_unselected_indicator,
                    R.drawable.indicator_unselected_dash
            );
        }

        if (Objects.equals(typedArray.getString(R.styleable.AutoImageSlider_ais_indicator_style), getResources().getString(R.string.style_dot))){
            selectedDot = typedArray.getResourceId(
                    R.styleable.AutoImageSlider_ais_selected_indicator,
                    R.drawable.indicator_selected_dot
            );

            unselectedDot = typedArray.getResourceId(
                    R.styleable.AutoImageSlider_ais_unselected_indicator,
                    R.drawable.indicator_unselected_dot
            );
        }

        titleBackground = typedArray.getResourceId(
                R.styleable.AutoImageSlider_ais_title_background,
                R.drawable.text_background
        );

        noDots = typedArray.getBoolean(R.styleable.AutoImageSlider_ais_dots_visible, false);

        if (typedArray.getString(R.styleable.AutoImageSlider_ais_text_align) != null){
            textAlign = typedArray.getString(R.styleable.AutoImageSlider_ais_text_align);
        }

        if (typedArray.getString(R.styleable.AutoImageSlider_ais_indicator_align) != null){
            indicatorAlign = typedArray.getString(R.styleable.AutoImageSlider_ais_indicator_align);
        }

        if (typedArray.getString(R.styleable.AutoImageSlider_ais_title_color) != null){
            titleColor = Integer.parseInt(typedArray.getString(R.styleable.AutoImageSlider_ais_title_color));
        }

        if (typedArray.getString(R.styleable.AutoImageSlider_ais_description_color) != null){
            descriptionColor = Integer.parseInt(typedArray.getString(R.styleable.AutoImageSlider_ais_description_color));
        }

    }

    public void setImageList(ArrayList<ImageSlidesModel> arrayList){
        viewAdapter = new ViewAdapter(context, arrayList, cornerRadius, errorImage, placeholder, titleBackground, textAlign, titleColor, descriptionColor, listener );
        setAdapter(arrayList);
    }

    public void setImageList(ArrayList<ImageSlidesModel> arrayList, ImageScaleType scaleType){
        viewAdapter = new ViewAdapter(context, arrayList, cornerRadius, errorImage, placeholder, titleBackground, scaleType, textAlign, titleColor, descriptionColor, listener);
        setAdapter(arrayList);
    }

    private void setAdapter(ArrayList<ImageSlidesModel> arrayList) {
        viewPager.setAdapter(viewAdapter);
        imageCount = arrayList.size();
        if (arrayList.size() != 0){
            if (!noDots){
                setupDots(arrayList.size());
            }
            if (autoCycle){
                startSliding(period);
            }
        }
    }

    public void setDefaultAnimation(){
        viewPager.setPageTransformer(true, new BackgroundToForeground());
    }

    public void startSlider(){

    }

    public void setSlideAnimation(ImageAnimationTypes animationType) {
        switch (animationType) {
            case ZOOM_IN:
                viewPager.setPageTransformer(true, new ZoomIn());
                break;
            case ZOOM_OUT:
                viewPager.setPageTransformer(true, new ZoomOut());
                break;
            case CUBE_IN:
                viewPager.setPageTransformer(true, new CubeIn());
                break;
            case CUBE_OUT:
                viewPager.setPageTransformer(true, new CubeOut());
                break;
            case FLIP_HORIZONTAL:
                viewPager.setPageTransformer(true, new FlipHorizontal());
                break;
            case FLIP_VERTICAL:
                viewPager.setPageTransformer(true, new FlipVertical());
                break;
            case ROTATE_UP:
                viewPager.setPageTransformer(true, new RotateUp());
                break;
            case ROTATE_DOWN:
                viewPager.setPageTransformer(true, new RotateDown());
                break;
            case FOREGROUND_TO_BACKGROUND:
                viewPager.setPageTransformer(true, new ForegroundToBackground());
                break;
            case BACKGROUND_TO_FOREGROUND:
                viewPager.setPageTransformer(true, new BackgroundToForeground());
                break;
            case TOSS:
                viewPager.setPageTransformer(true, new Toss());
                break;
            case GATE:
                viewPager.setPageTransformer(true, new Gate());
                break;
            case FIDGET_SPINNER:
                viewPager.setPageTransformer(true, new FidgetSpinner());
                break;
            default:
                viewPager.setPageTransformer(true, new DepthSlide());
                break;
        }
    }



    private void setupDots(int size) {
        pagerDots.setGravity(getAlignment(indicatorAlign));
        pagerDots.removeAllViews();
        dotsImages = new ImageView[size];

        for (int i = 0; i < size; i++) {
            dotsImages[i] = new ImageView(context);
            dotsImages[i].setImageDrawable(ContextCompat.getDrawable(context, unselectedDot));

            LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,0,10,0);
            pagerDots.addView(dotsImages[i], params);

        }

        dotsImages[0].setImageDrawable(ContextCompat.getDrawable(context, selectedDot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                for (ImageView dotImage : dotsImages){
                    dotImage.setImageDrawable(ContextCompat.getDrawable(context, unselectedDot));
                }
                dotsImages[position].setImageDrawable(ContextCompat.getDrawable(context, selectedDot));
                if (itemChangeListener != null)
                    itemChangeListener.onItemChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void startSliding(Long changeableInterval) {
        stopSliding();
        scheduleTimer(changeableInterval);
    }
    private void stopSliding() {
        swipeTimer.cancel();
        swipeTimer.purge();
    }
    private void scheduleTimer(long period) {
        setViewPageScroller(new PageScroller(context));

        Handler handler = new Handler();
        Runnable update = new Runnable() {
            public void run() {
                if (currentPage == imageCount) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            public void run() {
                handler.post(update);
            }
        }, delay, period);
    }

    public void setViewPageScroller(PageScroller pageScroller) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(this, pageScroller);
        } catch (NoSuchFieldException e) {
            // Handle NoSuchFieldException
        } catch (IllegalArgumentException e) {
            // Handle IllegalArgumentException
        } catch (IllegalAccessException e) {
            // Handle IllegalAccessException
        }
    }

    public int getAlignment(String textAlign) {
        if (textAlign.equals("RIGHT")) {
            return Gravity.END;
        } else if (textAlign.equals("LEFT")) {
            return Gravity.START;
        } else {
            return Gravity.CENTER;
        }
    }



    public void onItemClickListener(ItemsListener listener){
        this.itemClickListener = listener;
        viewAdapter.setItemClickListener(listener );
    }

    public void onItemChangeListener(ItemsListener listener){
        viewAdapter.setItemClickListener(listener );
    }

    public void onItemTouchListener(ItemsListener listener){
        this.touchListener = listener;
        viewAdapter.setItemTouchListener(touchListener );
    }
    public void onItemDoubleTapListener(ItemsListener listener){
        viewAdapter.setItemDoubleTapListener(listener );
    }
}
