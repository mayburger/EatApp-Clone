package com.mayburger.eatclone.ui.region

import androidx.databinding.ObservableArrayList
import androidx.hilt.lifecycle.ViewModelInject
import com.google.firebase.firestore.ktx.toObject
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RegionDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.rx.SchedulerProvider

class SelectRegionViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<SelectRegionNavigator>(dataManager, schedulerProvider) {

    override fun onEvent(obj: Any) {

    }

    val regionsViewModel = ObservableArrayList<ItemRegionViewModel>()

    fun getRegions() {
        dataManager.regions().addOnCompleteListener {
            if (it.isSuccessful) {
                for (i in it.result?.documents!!) {
                    regionsViewModel.add(ItemRegionViewModel(i.toObject<RegionDataModel>()))
                }
            }
        }
    }

    fun setRegion(region: RegionDataModel) {
        navigator?.showLoading()
        region.id?.toInt()?.let { dataManager.setUserRegion(it) }?.addOnCompleteListener {
            navigator?.hideLoading()
            if (it.isSuccessful) {
                dataManager.region = region
                val user = dataManager.user
                user.region = region.id.toLong()
                dataManager.user = user
                navigator?.onSelectRegion()
            } else {
                navigator?.onError(it.exception?.message)
            }
        }
    }
}