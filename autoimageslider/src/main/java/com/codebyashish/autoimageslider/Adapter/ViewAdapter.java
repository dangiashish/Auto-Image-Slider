package com.codebyashish.autoimageslider.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.codebyashish.autoimageslider.Enums.ImageActionTypes;
import com.codebyashish.autoimageslider.Enums.ImageScaleType;
import com.codebyashish.autoimageslider.Interfaces.ItemsListener;
import com.codebyashish.autoimageslider.Models.ImageSlidesModel;
import com.codebyashish.autoimageslider.PicassoTransformation;
import com.codebyashish.autoimageslider.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;

public class ViewAdapter extends PagerAdapter {

    private ArrayList<ImageSlidesModel> imageSlidesModelArrayList;
    private View view;
    private ImageView ivImage;
    private TextView tvTitle, tvDesc;
    private LinearLayout textBackgroundLayout;
    private Context context;
    private int radius;
    private int ivErrorImage;
    private int placeHolder;
    private int titleBackground;
    private ImageScaleType scaleType;
    ItemsListener listener;
    private String textAlign;
    private int titleColor, descColor;
    private long currentTouchTime = 0;
    private long lastTouchTime = 0;

    public ViewAdapter(Context context, ArrayList<ImageSlidesModel> arrayList, int cornerRadius,
                       int errorImage, int placeholder, int titleBackground, String textAlign, int titleColor, int descriptionColor, ItemsListener listener) {
        this.context = context;
        this.imageSlidesModelArrayList = arrayList;
        this.radius = cornerRadius;
        this.ivErrorImage = errorImage;
        this.placeHolder = placeholder;
        this.titleBackground = titleBackground;
        this.textAlign = textAlign;
        this.titleColor = titleColor;
        this.descColor = descriptionColor;
        this.listener = listener;
    }

    public ViewAdapter(Context context, ArrayList<ImageSlidesModel> arrayList, int cornerRadius,
                       int errorImage, int placeholder, int titleBackground, ImageScaleType scaleType, String textAlign, int titleColor, int descriptionColor
            , ItemsListener listener) {
        this.context = context;
        this.imageSlidesModelArrayList = arrayList;
        this.radius = cornerRadius;
        this.ivErrorImage = errorImage;
        this.placeHolder = placeholder;
        this.titleBackground = titleBackground;
        this.textAlign = textAlign;
        this.titleColor = titleColor;
        this.descColor = descriptionColor;
        this.scaleType = scaleType;
        this.listener = listener;
    }


    @Override
    public int getCount() {
        return imageSlidesModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        view = LayoutInflater.from(context).inflate(R.layout.item_pager, container, false);
        ImageSlidesModel model = imageSlidesModelArrayList.get(position);
        ivImage = view.findViewById(R.id.ivImage);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvDesc = view.findViewById(R.id.tvDesc);
        textBackgroundLayout = view.findViewById(R.id.llBackground);
        tvTitle.setTextColor(titleColor);
        tvDesc.setTextColor(descColor);

        tvTitle.setSelected(true);
        tvDesc.setSelected(true);
        textBackgroundLayout.setVisibility(View.GONE);

        if (model.getTitle() != null && !model.getTitle().equals("")) {
            textBackgroundLayout.setVisibility(View.VISIBLE);
            tvTitle.setText(model.getTitle());
            textBackgroundLayout.setBackgroundResource(titleBackground);
            tvTitle.setGravity(getGravityFromAlign(textAlign));
            textBackgroundLayout.setGravity(getGravityFromAlign(textAlign));
            tvDesc.setVisibility(View.GONE);
        } else if (model.getDescription() != null && !model.getDescription().equals("")) {
            textBackgroundLayout.setVisibility(View.VISIBLE);
            tvDesc.setText(model.getTitle());
            textBackgroundLayout.setBackgroundResource(titleBackground);
            tvDesc.setGravity(getGravityFromAlign(textAlign));
            textBackgroundLayout.setGravity(getGravityFromAlign(textAlign));
        } else if ((model.getTitle() != null && !model.getTitle().equals("")) && ((model.getDescription() != null) && !model.getDescription().equals(""))) {
            tvTitle.setText(model.getTitle());
            textBackgroundLayout.setVisibility(View.VISIBLE);
            tvDesc.setText(model.getTitle());
            textBackgroundLayout.setBackgroundResource(titleBackground);
            tvDesc.setGravity(getGravityFromAlign(textAlign));
            textBackgroundLayout.setGravity(getGravityFromAlign(textAlign));
        } else {
            textBackgroundLayout.setVisibility(View.INVISIBLE);
        }

        Log.i("TAG", model.getTitle() + " " + model.getDescription());

        RequestCreator picasso;
        if (model.getImgUrl() != null) {
            picasso = Picasso.get().load(model.getImgUrl());
        } else {
            picasso = Picasso.get().load(model.getImgPath());
        }

        if (scaleType != null) {
            if (model.getImageScaleType() == ImageScaleType.FIT || scaleType == ImageScaleType.FIT) {
                picasso.fit();
            } else if (model.getImageScaleType() == ImageScaleType.CENTER_CROP || scaleType == ImageScaleType.CENTER_CROP) {
                picasso.fit().centerCrop();
            } else if (model.getImageScaleType() == ImageScaleType.CENTER_INSIDE || scaleType == ImageScaleType.CENTER_INSIDE) {
                picasso.fit().centerInside();
            }
        }

        picasso.transform(new PicassoTransformation(radius, 0, PicassoTransformation.CornerType.ALL)).placeholder(placeHolder)
                .error(ivErrorImage).into(ivImage);

        container.addView(view);

        ivImage.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClicked(position);
            }
        });

        if (listener != null) {
            ivImage.setOnTouchListener((v, event) -> {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        listener.onTouched(ImageActionTypes.MOVE, position);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        listener.onTouched(ImageActionTypes.DOWN, position);
                        break;
                    case MotionEvent.ACTION_UP:
                        listener.onTouched(ImageActionTypes.UP, position);
                        break;
                }
                return false;
            });
        }

        return view;

    }

    public void setItemClickListener(ItemsListener itemChangeListener) {
        this.listener = itemChangeListener;
    }

    public void setItemChangeListener(ItemsListener itemChangeListener) {
        this.listener = itemChangeListener;
    }

    public void setItemTouchListener(ItemsListener itemChangeListener) {
        this.listener = itemChangeListener;
    }

    public void setItemDoubleTapListener(ItemsListener itemChangeListener) {
        this.listener = itemChangeListener;
    }

    public int getGravityFromAlign(String textAlign) {
        switch (textAlign) {
            case "RIGHT":
                return Gravity.END;
            case "CENTER":
                return Gravity.CENTER;
            default:
                return Gravity.START;
        }
    }
}
