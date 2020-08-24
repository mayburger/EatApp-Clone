package com.mayburger.eatclone.util

import android.os.Build

object AppUtil {

    val isAndroidN: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

    val isEmulator: Boolean
        get() = Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk" == Build.PRODUCT
}