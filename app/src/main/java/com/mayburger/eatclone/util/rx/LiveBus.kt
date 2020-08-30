package com.mayburger.eatclone.util.rx

import androidx.lifecycle.MutableLiveData

object LiveBus {

    private val liveDataEvent = MutableLiveData<Event>()

    fun post(event: Any) {
        this.liveDataEvent.postValue(Event(event))
    }

    fun getDefault(): MutableLiveData<Event> {
        return liveDataEvent
    }

    class Event(private val value:Any) {
//        private var isConsumed = false
//
//        fun setConsumed() {
//            isConsumed = true
//        }
//
//        fun isConsumed(): Boolean {
//            return isConsumed
//        }

        fun getValue(): Any {
            return this.value
        }
    }
}