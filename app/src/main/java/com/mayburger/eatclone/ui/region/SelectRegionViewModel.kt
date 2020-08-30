package com.mayburger.eatclone.ui.region

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.model.RegionDataModel
import com.mayburger.eatclone.ui.base.BaseViewModel
import com.mayburger.eatclone.util.constants.RecyclerConstants
import com.mayburger.eatclone.util.rx.SchedulerProvider
import kotlinx.coroutines.Dispatchers.IO

class SelectRegionViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<SelectRegionNavigator>(dataManager, schedulerProvider) {



    val layoutManager = ObservableField(RecyclerConstants.VERTICAL_LAYOUT_MANAGER)

    val regions = liveData(IO) {
        try {
            emit(dataManager.getRegions())
            layoutManager.set(RecyclerConstants.GRID_2_LAYOUT_MANAGER)
        } catch (e: Exception) {
            navigator?.onError(e.message)
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