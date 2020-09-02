package com.mayburger.eatclone.util.ext

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mayburger.eatclone.model.MenuDataModel
import java.io.*


fun Int.toStringJson(mContext: Context): String {
    val `is`: InputStream = mContext.resources.openRawResource(this)
    val writer: Writer = StringWriter()
    val buffer = CharArray(1024)
    `is`.use { `is` ->
        val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
        var n: Int
        while (reader.read(buffer).also { n = it } != -1) {
            writer.write(buffer, 0, n)
        }
    }
    return writer.toString()
}

operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>) {
    val value = this.value ?: arrayListOf()
    value.addAll(values)
    this.value = value
}

operator fun MutableLiveData<ArrayList<MenuDataModel>>.plus(values: MenuDataModel) {
    val value = this.value ?: arrayListOf()
    var hasMenu = false
    var index = 0
    value.mapIndexed { index, menuDataModel ->
        if (menuDataModel.id == values.id) {
            hasMenu = true
        }
    }
    if (hasMenu) {
        value[index] = values
    } else {
        value.add(values)
    }
    this.value = value
}

fun ArrayList<MenuDataModel>.indexWithId(id: String?): Int? {
    var index: Int? = null
    this.mapIndexed { i, it ->
        if (it.id == id) {
            index = i
        }
    }
    return index
}

fun MutableLiveData<ArrayList<MenuDataModel>>.change(menu: MenuDataModel) {
    val value = this.value ?: arrayListOf()
    value.indexWithId(menu.id)?.let {
        if (menu.quantity == 0) {
            value.removeAt(it)
        } else {
            value.set(it, menu)
        }
    } ?: kotlin.run {
        value.add(menu)
    }
    this.value = value
}



