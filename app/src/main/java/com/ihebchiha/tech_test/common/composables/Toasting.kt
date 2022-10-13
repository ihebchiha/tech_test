package com.ihebchiha.tech_test.common

import android.content.Context
import android.os.Build
import android.widget.Toast

const val errorMessage = "Error occurred while processing your request..."
fun createToast(context: Context, message: String?){
    val mToast: Toast = Toast.makeText(context, message ?: errorMessage, Toast.LENGTH_SHORT)

    // cancel previous toast and display correct answer toast
    try {
        if (mToast.view!!.isShown) {
            mToast.cancel()
        }
        // cancel same toast only on Android P and above, to avoid IllegalStateException on addView
        if (Build.VERSION.SDK_INT >= 28 && mToast.view!!.isShown) {
            mToast.cancel()
        }
        mToast.show()
    } catch (e: Exception) {
        e.printStackTrace()
        mToast.show() //This line will show toast if previous toast is null
    }
}