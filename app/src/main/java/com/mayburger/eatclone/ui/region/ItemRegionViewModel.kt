package com.mayburger.eatclone.ui.region

import androidx.databinding.ObservableField
import com.mayburger.eatclone.model.RegionDataModel

class ItemRegionViewModel (val region:RegionDataModel? = RegionDataModel()){
    val name = ObservableField(region?.name)
    val country = ObservableField(region?.country)
    val image = ObservableField(region?.image)
}