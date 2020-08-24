package com.mayburger.eatclone.util

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.mayburger.eatclone.R


object ViewUtils {

    private var screenWidth = 0
    private var screenHeight = 0

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun getProgressDialog(context: Context, message: CharSequence): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage(message)
        progressDialog.isIndeterminate = true
        val drawable = ProgressBar(context).indeterminateDrawable.mutate()
        drawable.setColorFilter(context.resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
        progressDialog.setIndeterminateDrawable(drawable)
        progressDialog.setCancelable(false)
        return progressDialog
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            activity.findViewById<View>(R.id.content).windowToken,
            0
        )
    }

    fun showKeyboard(view: View) {
        val imm = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    fun getDialog(context: Context,
                  title: String,
                  message: String,
                  isCancelable: Boolean,
                  positiveButton: String,
                  actionPositive: Runnable?,
                  negativeButton: String?,
                  actionNegative: Runnable?): AlertDialog {
        val builder = AlertDialog.Builder(context, R.style.ThemeDialogCustom)
        if(title.isEmpty().not()) builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButton) { dialog, _ ->
            dialog.dismiss()
            actionPositive?.run()
        }
        negativeButton?.let {
            builder.setNegativeButton(negativeButton) { dialog, _ ->
                dialog.dismiss()
                actionNegative?.run()
            }
        }
        val alertDialog = builder.create()
        alertDialog.setCancelable(isCancelable)
        (alertDialog.window)?.setLayout(getScreenWidth(context) - dpToPx(32),
            ViewGroup.LayoutParams.WRAP_CONTENT)
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(context.resources.getColor(R.color.colorPrimary))
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(context.resources.getColor(R.color.colorPrimary))
        }
        return alertDialog
    }

    fun getNeutralDialog(context: Context,
                         title: String,
                         message: String,
                         isCancelable: Boolean,
                         neutralButton: String): AlertDialog {
        val builder = AlertDialog.Builder(context, R.style.ThemeDialogCustom)
        if(title.isEmpty().not()) builder.setTitle(title)
        builder.setMessage(message)
        builder.setNeutralButton(neutralButton) { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.setCancelable(isCancelable)
        (alertDialog.window)?.setLayout(getScreenWidth(context) - dpToPx(32),
            ViewGroup.LayoutParams.WRAP_CONTENT)
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(context.resources.getColor(R.color.colorPrimary))
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(context.resources.getColor(R.color.colorPrimary))
        }
        return alertDialog
    }

    fun getScreenWidth(context: Context): Int {
        if (screenWidth == 0) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            screenWidth = size.x
        }
        return screenWidth
    }

}


fun View.hideKeyboard() {
    val imm = this.context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(
        (this.context as Activity).findViewById<View>(R.id.content).windowToken,
        0
    )
}

fun View.showKeyboard() {
    val imm = this.context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

