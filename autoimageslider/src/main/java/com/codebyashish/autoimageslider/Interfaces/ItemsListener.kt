package com.codebyashish.autoimageslider.Interfaces;

import com.codebyashish.autoimageslider.Enums.ImageActionTypes;
import com.codebyashish.autoimageslider.Models.ImageSlidesModel;

public interface ItemsListener {
    void onItemChanged (int position);
    void onTouched (ImageActionTypes actionTypes, int position);
    void onItemClicked (int position);
}
