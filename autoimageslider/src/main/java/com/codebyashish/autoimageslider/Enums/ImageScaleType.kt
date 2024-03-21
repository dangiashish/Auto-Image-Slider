package com.codebyashish.autoimageslider.Enums;
public enum ImageScaleType {
    FIT("fit"),
    CENTER_CROP("centerCrop"),
    CENTER_INSIDE("centerInside");

    private final String value;

    ImageScaleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
