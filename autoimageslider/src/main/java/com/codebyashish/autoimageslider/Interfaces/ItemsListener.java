package com.codebyashish.autoimageslider.Interfaces;

import com.codebyashish.autoimageslider.Enums.ImageActionTypes;

public interface ItemsListener {
    void onItemChanged (int position);
    void onTouched (ImageActionTypes actionTypes, int position);
    void onItemClicked (int position);
}
