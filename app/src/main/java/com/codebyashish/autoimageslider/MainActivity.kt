package com.codebyashish.autoimageslider

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codebyashish.autoimageslider.Enums.ImageActionTypes
import com.codebyashish.autoimageslider.Enums.ImageAnimationTypes
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Interfaces.ItemsListener
import com.codebyashish.autoimageslider.Models.ImageSlidesModel


class MainActivity : AppCompatActivity(), ItemsListener {


    private lateinit var listener: ItemsListener
    var autoImageList = ArrayList<ImageSlidesModel>()
    private lateinit var autoImageSlider: AutoImageSlider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        autoImageSlider = findViewById(R.id.autoImageSlider)
        try {
            autoImageList.add(
                ImageSlidesModel(
                    R.drawable.ic_launcher_foreground, "https://google.com", "Face",
                    ImageScaleType.FIT
                )
            )
            autoImageList.add(
                ImageSlidesModel(
                    "",
                    "https://google.com",
                    "title 2",
                    ImageScaleType.FIT
                )
            )
            autoImageSlider.setImageList(autoImageList, ImageScaleType.FIT)
            autoImageSlider.setSlideAnimation(ImageAnimationTypes.ZOOM_IN)
        } catch (e: ExceptionsClass) {
            e.printStackTrace()
        }
        autoImageSlider.onItemClickListener(this)
    }

    override fun onItemChanged(position: Int) {

    }

    override fun onTouched(actionTypes: ImageActionTypes?, position: Int) {

    }

    override fun onItemClicked(position: Int) {
        val model = autoImageList[position]
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.clickUrl))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}