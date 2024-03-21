package com.codebyashish.autoimageslider.Interfaces

import com.codebyashish.autoimageslider.Enums.ImageActionTypes

interface ItemsListener {
    fun onItemChanged(position: Int)
    fun onTouched(actionTypes: ImageActionTypes?, position: Int)
    fun onItemClicked(position: Int)
}
