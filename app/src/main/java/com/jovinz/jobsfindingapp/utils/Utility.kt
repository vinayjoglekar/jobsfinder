package com.jovinz.jobsfindingapp.utils

import android.os.SystemClock

object Utility {

    const val debounceTime: Long = 1000L
    var mLastClickTime = 0L

    inline fun onSingleTap(block: () -> Unit) {
        if (SystemClock.elapsedRealtime() - mLastClickTime > debounceTime) {
            block()
        }
        mLastClickTime = SystemClock.elapsedRealtime()
    }
}