package com.codebyashish.autoimageslider.Models

import android.util.Log
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.ExceptionsClass

class ImageSlidesModel {
    var imgPath = 0
    var imgUrl: String? = null
    var clickUrl: String? = null
    var imageScaleType: ImageScaleType? = null
    var title: String? = null
    var description: String? = null

    /**
     * For image paths from drawables
     */
    constructor(imgPath: Int) {
        this.imgPath = imgPath
    }

    constructor(imgPath: Int, imageScaleType: ImageScaleType?) {
        this.imgPath = imgPath
        this.imageScaleType = imageScaleType
    }

    constructor(imgPath: Int, imageScaleType: ImageScaleType?, title: String?) {
        this.imgPath = imgPath
        this.imageScaleType = imageScaleType
        this.title = title
    }

    constructor(imgPath: Int, title: String?) {
        this.imgPath = imgPath
        this.title = title
    }

    constructor(
        imgPath: Int,
        imageScaleType: ImageScaleType?,
        title: String?,
        description: String?
    ) {
        this.imgPath = imgPath
        this.imageScaleType = imageScaleType
        this.title = title
        this.description = description
    }

    constructor(imgPath: Int, clickUrl: String, scaleType: ImageScaleType) {
        this.imgPath = imgPath
        this.clickUrl = clickUrl
        imageScaleType = scaleType
        if (!clickUrl.startsWith("https://") && !clickUrl.startsWith("http://")) {
            this.clickUrl = "https://"
        }
    }

    constructor(imgPath: Int, clickUrl: String, title: String, scaleType: ImageScaleType) {
        this.imgPath = imgPath
        this.clickUrl = clickUrl
        this.title = title
        imageScaleType = scaleType
        if (!clickUrl.startsWith("https://") && !clickUrl.startsWith("http://")) {
            this.clickUrl = "https://"
        }
    }

    /**
     * For image paths from urls or internet
     */
    constructor(imgUrl: String, clickUrl: String, scaleType: ImageScaleType) {
        this.imgUrl = imgUrl
        this.clickUrl = clickUrl
        imageScaleType = scaleType
        if (!clickUrl.startsWith("https://") && !clickUrl.startsWith("http://")) {
            this.clickUrl = "https://"
        }
    }

    constructor(imgUrl: String, clickUrl: String, title: String, scaleType: ImageScaleType) {
        this.imgUrl = imgUrl
        this.clickUrl = clickUrl
        this.title = title
        imageScaleType = scaleType
        if (!clickUrl.startsWith("https://") && !clickUrl.startsWith("http://")) {
            this.clickUrl = "https://"
        }
    }

    constructor(imgUrl: String?) {
        this.imgUrl = imgUrl
    }

    constructor(imgUrl: String?, imageScaleType: ImageScaleType?) {
        this.imgUrl = imgUrl
        this.imageScaleType = imageScaleType
    }

    constructor(imgUrl: String?, imageScaleType: ImageScaleType?, title: String?) {
        this.imgUrl = imgUrl
        this.imageScaleType = imageScaleType
        this.title = title
    }

    constructor(imgUrl: String?, title: String?) {
        this.imgUrl = imgUrl
        this.title = title
    }

    constructor(imgUrl: String?, title: String?, description: String?) {
        this.imgUrl = imgUrl
        this.title = title
        this.description = description
    }

    constructor(
        imgUrl: String?,
        imageScaleType: ImageScaleType?,
        title: String?,
        description: String?
    ) {
        this.imgUrl = imgUrl
        this.imageScaleType = imageScaleType
        this.title = title
        this.description = description
    }
}
