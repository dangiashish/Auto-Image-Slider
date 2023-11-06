package com.codebyashish.autoimageslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codebyashish.autoimageslider.Enums.ImageAnimationTypes
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Interfaces.ItemsListener
import com.codebyashish.autoimageslider.Models.ImageSlidesModel


var listener: ItemsListener? = null
var autoImageList = ArrayList<ImageSlidesModel>()
class MainActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val autoImageSlider: AutoImageSlider = findViewById<AutoImageSlider>(R.id.autoImageSlider)
        try {
            autoImageList.add(
                ImageSlidesModel(
                    "https://as1.ftcdn.net/v2/jpg/05/35/47/38/1000_F_535473874_OWCa2ohzXXNZgqnlzF9QETsnbrSO9pFS.jpg",
                    "title 1"
                )
            )
            autoImageList.add(
                ImageSlidesModel(
                    "https://as1.ftcdn.net/v2/jpg/05/29/37/22/1000_F_529372232_2Z75XLUgwHQQmtsgeWwGdpdCx4inCPbP.jpg",
                    "title 2"
                )
            )
            autoImageSlider.setImageList(autoImageList, ImageScaleType.FIT)
            autoImageSlider.setSlideAnimation(ImageAnimationTypes.ZOOM_IN)
        } catch (e: ExceptionsClass) {
            throw RuntimeException(e)
        }
        autoImageSlider.onItemClickListener(listener)
    }

}