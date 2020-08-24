package com.mayburger.eatclone.util.rx

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T>
}
