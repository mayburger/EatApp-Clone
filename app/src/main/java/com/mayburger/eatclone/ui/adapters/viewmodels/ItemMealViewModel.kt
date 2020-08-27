package com.mayburger.eatclone.ui.adapters.viewmodels

import com.mayburger.eatclone.model.MealDataModel

class ItemMealViewModel (val data:MealDataModel){

    var navigator: Callback? = null

    fun onClickItem(){
        navigator?.onClickItem()
    }

    interface Callback{
        fun onClickItem()
    }
}