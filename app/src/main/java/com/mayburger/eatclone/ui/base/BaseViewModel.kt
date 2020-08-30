package com.mayburger.eatclone.ui.base

import android.graphics.Color
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mayburger.eatclone.data.DataManager
import com.mayburger.eatclone.util.rx.RxBus
import com.mayburger.eatclone.util.rx.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference


abstract class BaseViewModel<N>(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val TAG = "BaseViewModel"

    private lateinit var mNavigator: WeakReference<N?>
    var navigator: N?
        get() = mNavigator.get()
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    var lifecycleOwner: LifecycleOwner? = null
    var colorSchemeResource = intArrayOf(Color.parseColor("#00A85F"))

    //it's must be inject from dagger
    val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(
            RxBus.getDefault().toObservables()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ obj ->
                    onEvent(obj)
                }, { it.printStackTrace() })
        )
    }

    abstract fun onEvent(obj: Any)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
