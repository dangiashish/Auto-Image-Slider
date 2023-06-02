package com.codebyashish.autoimageslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codebyashish.autoimageslider.Enums.ImageActionTypes
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Interfaces.ItemsListener
import com.codebyashish.autoimageslider.Models.ImageSlidesModel

class MainActivity : AppCompatActivity() , ItemsListener {
    private var listener : ItemsListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listener = this

        val autoImageList : ArrayList<ImageSlidesModel> = ArrayList()
        val autoImageSlider = findViewById<AutoImageSlider>(R.id.autoImageSlider)
        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/237/200/300", "First image"))
        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/238/200/300", ""))
        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/239/200/300", "Third image"))
        autoImageSlider.setImageList(autoImageList, ImageScaleType.FIT)
        autoImageSlider.setDefaultAnimation()

        autoImageSlider.onItemClickListener(listener)

    }

    override fun onItemChanged(position: Int) {
// do what you want on item change event
    }

    override fun onTouched(actionTypes: ImageActionTypes?, position: Int) {
// do what you want on item touch event
    }

    override fun onItemClicked(position: Int) {
        // do what you want on click event
    }
}