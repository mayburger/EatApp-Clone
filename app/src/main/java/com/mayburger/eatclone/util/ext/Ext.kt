package com.mayburger.eatclone.util.ext

import android.content.Context
import java.io.*


fun Int.toStringJson(mContext:Context):String {
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

