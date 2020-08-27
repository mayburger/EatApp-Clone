package com.mayburger.eatclone.util.binding

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mayburger.eatclone.util.AuthEditText

object EditTextBinding {

    @BindingAdapter("actionDone")
    @JvmStatic
    fun bindActionDone(editText: AuthEditText, runnable: View.OnClickListener?) {
        editText.setOnEditorActionListener(TextView.OnEditorActionListener { p0, actionId, p2 ->
            if (editText.text.toString().isEmpty().not()) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    runnable?.onClick(editText)
                }
            }
            false
        })
    }
}