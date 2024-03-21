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
        if (imgPath == 0) {
            throw ExceptionsClass("Image path cannot be blank or null")
        }
        this.imgPath = imgPath
    }

    constructor(imgPath: Int, imageScaleType: ImageScaleType?) {
        if (imgPath == 0) {
            throw ExceptionsClass("Image path cannot be blank or null")
        }
        this.imgPath = imgPath
        this.imageScaleType = imageScaleType
    }

    constructor(imgPath: Int, imageScaleType: ImageScaleType?, title: String?) {
        if (imgPath == 0) {
            throw ExceptionsClass("Image path cannot be blank or null")
        }
        this.imgPath = imgPath
        this.imageScaleType = imageScaleType
        this.title = title
    }

    constructor(imgPath: Int, title: String?) {
        if (imgPath == 0) {
            throw ExceptionsClass("Image path cannot be blank or null")
        }
        this.imgPath = imgPath
        this.title = title
    }

    constructor(
        imgPath: Int,
        imageScaleType: ImageScaleType?,
        title: String?,
        description: String?
    ) {
        if (imgPath == 0) {
            throw ExceptionsClass("Image path cannot be blank or null")
        }
        this.imgPath = imgPath
        this.imageScaleType = imageScaleType
        this.title = title
        this.description = description
    }

    /**
     * For image paths from urls or internet
     */
    constructor(imgUrl: String?, clickUrl: String?, scaleType: ImageScaleType?) {
        if (imgUrl == null || imgUrl == "") {
            throw ExceptionsClass("Image url cannot be blank or null")
        }
        if (clickUrl == null || clickUrl == "") {
            throw ExceptionsClass("Image url cannot be blank or null")
        }
        if (!clickUrl.startsWith("https://") || !clickUrl.startsWith("http://")) {
            throw ExceptionsClass("Click url is not valid")
        }
        this.imgUrl = imgUrl
        this.clickUrl = clickUrl
        imageScaleType = scaleType
    }

    constructor(imgUrl: String?, clickUrl: String?, title: String?, scaleType: ImageScaleType?) {
        Log.d("ashishji", clickUrl + "")
        if (imgUrl == null || imgUrl.isEmpty()) {
            throw ExceptionsClass("Image URL cannot be blank or null")
        } else if (clickUrl == null || clickUrl.isEmpty()) {
            throw ExceptionsClass("Click URL cannot be blank or null")
        } else if (!clickUrl.startsWith("https://") && !clickUrl.startsWith("http://")) {
            throw ExceptionsClass("Click URL is not valid")
        }
        this.imgUrl = imgUrl
        this.clickUrl = clickUrl
        this.title = title
        imageScaleType = scaleType
    }

    constructor(imgUrl: String?) {
        if (imgUrl == null || imgUrl == "") {
            throw ExceptionsClass("Image url cannot be blank or null")
        }
        this.imgUrl = imgUrl
    }

    constructor(imgUrl: String?, imageScaleType: ImageScaleType?) {
        if (imgUrl == null || imgUrl == "") {
            throw ExceptionsClass("Image url cannot be blank or null")
        }
        this.imgUrl = imgUrl
        this.imageScaleType = imageScaleType
    }

    constructor(imgUrl: String?, imageScaleType: ImageScaleType?, title: String?) {
        if (imgUrl == null || imgUrl == "") {
            throw ExceptionsClass("Image url cannot be blank or null")
        }
        this.imgUrl = imgUrl
        this.imageScaleType = imageScaleType
        this.title = title
    }

    constructor(imgUrl: String?, title: String?) {
        if (imgUrl == null || imgUrl == "") {
            throw ExceptionsClass("Image url cannot be blank or null")
        }
        this.imgUrl = imgUrl
        this.title = title
    }

    constructor(imgUrl: String?, title: String?, description: String?) {
        if (imgUrl == null || imgUrl == "") {
            throw ExceptionsClass("Image url cannot be blank or null")
        }
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
        if (imgUrl == null || imgUrl == "") {
            throw ExceptionsClass("Image url cannot be blank or null")
        }
        this.imgUrl = imgUrl
        this.imageScaleType = imageScaleType
        this.title = title
        this.description = description
    }
}
