package com.codebyashish.autoimageslider.Models;

import com.codebyashish.autoimageslider.Enums.ImageScaleType;
import com.codebyashish.autoimageslider.ExceptionsClass;

public class ImageSlidesModel {
    int imgPath;
    private String imgUrl;
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
