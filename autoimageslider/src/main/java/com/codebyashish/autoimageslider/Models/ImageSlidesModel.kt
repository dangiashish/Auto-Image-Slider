package com.codebyashish.autoimageslider.Models;

import android.util.Log;

import com.codebyashish.autoimageslider.Enums.ImageScaleType;
import com.codebyashish.autoimageslider.ExceptionsClass;

public class ImageSlidesModel {
    int imgPath;
    private String imgUrl, clickUrl;
    private ImageScaleType imageScaleType;
    private String  title, description;

    public int getImgPath() {
        return imgPath;
    }

    public void setImgPath(int imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public ImageScaleType getImageScaleType() {
        return imageScaleType;
    }

    public void setImageScaleType(ImageScaleType imageScaleType) {
        this.imageScaleType = imageScaleType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    /**
    *  For image paths from drawables
    * */

    public ImageSlidesModel(int imgPath) throws ExceptionsClass {
        if (imgPath == 0){
            throw new ExceptionsClass("Image path cannot be blank or null");
        }
        this.imgPath = imgPath;

    }
    public ImageSlidesModel(int imgPath, ImageScaleType imageScaleType) throws ExceptionsClass{
        if (imgPath == 0){
            throw new ExceptionsClass("Image path cannot be blank or null");
        }
        this.imgPath = imgPath;
        this.imageScaleType = imageScaleType;
    }

    public ImageSlidesModel(int imgPath, ImageScaleType imageScaleType, String title) throws ExceptionsClass{
        if (imgPath == 0){
            throw new ExceptionsClass("Image path cannot be blank or null");
        }

        this.imgPath = imgPath;
        this.imageScaleType = imageScaleType;
        this.title = title;
    }

    public ImageSlidesModel(int imgPath, String title) throws ExceptionsClass{
        if (imgPath == 0){
            throw new ExceptionsClass("Image path cannot be blank or null");
        }
        this.imgPath = imgPath;
        this.title = title;
    }

    public ImageSlidesModel(int imgPath, ImageScaleType imageScaleType, String title, String description) throws ExceptionsClass{
        if (imgPath == 0){
            throw new ExceptionsClass("Image path cannot be blank or null");
        }
        this.imgPath = imgPath;
        this.imageScaleType = imageScaleType;
        this.title = title;
        this.description = description;
    }


    /**
     *  For image paths from urls or internet
     * */

    public ImageSlidesModel(String imgUrl, String clickUrl, ImageScaleType scaleType) throws ExceptionsClass{
        if (imgUrl == null || imgUrl.equals("")){
            throw new ExceptionsClass("Image url cannot be blank or null");
        }
        if (clickUrl == null || clickUrl.equals("")){
            throw new ExceptionsClass("Image url cannot be blank or null");
        }
        if (!clickUrl.startsWith("https://") || !clickUrl.startsWith("http://")){
            throw new ExceptionsClass("Click url is not valid");
        }
        this.imgUrl = imgUrl;
        this.clickUrl = clickUrl;
        this.imageScaleType = scaleType;
    }

    public ImageSlidesModel(String imgUrl, String clickUrl, String title, ImageScaleType scaleType) throws ExceptionsClass{
        Log.d("ashishji", clickUrl+"");
        if (imgUrl == null || imgUrl.isEmpty()) {
            throw new ExceptionsClass("Image URL cannot be blank or null");
        } else if (clickUrl == null || clickUrl.isEmpty()) {
            throw new ExceptionsClass("Click URL cannot be blank or null");
        } else if (!clickUrl.startsWith("https://") && !clickUrl.startsWith("http://")) {
            throw new ExceptionsClass("Click URL is not valid");
        }

        this.imgUrl = imgUrl;
        this.clickUrl = clickUrl;
        this.title = title;
        this.imageScaleType = scaleType;
    }
    public ImageSlidesModel(String imgUrl) throws ExceptionsClass{
        if (imgUrl == null || imgUrl.equals("")){
            throw new ExceptionsClass("Image url cannot be blank or null");
        }
        this.imgUrl = imgUrl;
    }
    public ImageSlidesModel(String imgUrl, ImageScaleType imageScaleType) throws ExceptionsClass{
        if (imgUrl == null || imgUrl.equals("")){
            throw new ExceptionsClass("Image url cannot be blank or null");
        }

        this.imgUrl = imgUrl;
        this.imageScaleType = imageScaleType;
    }

    public ImageSlidesModel(String imgUrl, ImageScaleType imageScaleType, String title) throws ExceptionsClass{
        if (imgUrl == null || imgUrl.equals("")){
            throw new ExceptionsClass("Image url cannot be blank or null");
        }
        this.imgUrl = imgUrl;
        this.imageScaleType = imageScaleType;
        this.title = title;
    }

    public ImageSlidesModel(String imgUrl, String title) throws ExceptionsClass {
        if (imgUrl == null || imgUrl.equals("")){
            throw new ExceptionsClass("Image url cannot be blank or null");
        }
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public ImageSlidesModel(String imgUrl, String title, String description) throws ExceptionsClass {
        if (imgUrl == null || imgUrl.equals("")){
            throw new ExceptionsClass("Image url cannot be blank or null");
        }
        this.imgUrl = imgUrl;
        this.title = title;
        this.description = description;
    }

    public ImageSlidesModel(String imgUrl, ImageScaleType imageScaleType, String title, String description) throws ExceptionsClass{
        if (imgUrl == null || imgUrl.equals("")){
            throw new ExceptionsClass("Image url cannot be blank or null");
        }
        this.imgUrl = imgUrl;
        this.imageScaleType = imageScaleType;
        this.title = title;
        this.description = description;
    }
}
