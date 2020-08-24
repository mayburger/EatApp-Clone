package com.mayburger.eatclone.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import com.mayburger.eatclone.R
import kotlinx.android.synthetic.main.auth_edit_text.view.*


class AuthEditText : LinearLayout {

    var text: String?
        get() = edit.text.toString()
        set(value) {
            edit.setText(value)
        }

    fun addTextChangedListener(listener: TextWatcher) =
        edit.addTextChangedListener(listener)

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener){
        edit.setOnEditorActionListener(listener)
    }

    internal var ldrawable: Drawable? = null
    internal var title: String? = null
    internal var inputType: Int? = null
    internal var hint: String? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.auth_edit_text, this)
//        edit!!.setHintTextColor(resources.getColor(R.color.colorTextHint))

        setOnClickListener {
            edit.requestFocus()
            edit.showKeyboard()
        }

        val a = getContext().obtainStyledAttributes(attrs, R.styleable.AuthEditText)

        val n = a.indexCount
        for (i in 0 until n) {
            val attr = a.getIndex(i)
            when (attr) {
                R.styleable.AuthEditText_android_src -> {
                    ldrawable = a.getDrawable(attr)
                    if (ldrawable != null) {
                        image!!.setImageDrawable(ldrawable)
                        image!!.visibility = View.VISIBLE
                    } else{
                        image!!.visibility = View.GONE
                    }
                }
                R.styleable.AuthEditText_android_hint ->{
                    hint = a.getString(attr)
                    mHint.text = hint
                    edit.addTextChangedListener(object:TextWatcher{
                        override fun afterTextChanged(p0: Editable?) {

                        }

                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            if (p0.isNullOrEmpty()){
                                mHint.visibility = View.VISIBLE
                            } else{
                                mHint.visibility = View.GONE
                            }
                        }
                    })
                }
                R.styleable.AuthEditText_title ->{
                    title = a.getString(attr)
                    tvTitle.text = title
                }
                R.styleable.AuthEditText_android_inputType -> {
                    inputType = a.getInteger(attr, 0)
                    edit!!.inputType = inputType!!
                    if (inputType == 129){
                        mVisibility.visibility = View.VISIBLE
                        mVisibility.setOnClickListener {
                            if (inputType == 129){
                                inputType = InputType.TYPE_CLASS_TEXT
                                mVisibility.setImageResource(R.drawable.ic_visible)
                                edit!!.inputType = inputType!!
                            } else{
                                inputType = 129
                                edit!!.inputType = inputType!!
                                mVisibility.setImageResource(R.drawable.ic_invisible)
                            }
                        }
                    }
                }
            }
        }
        a.recycle()
    }
    @InverseBindingMethods(
        InverseBindingMethod(
            type = AuthEditText::class,
            attribute = "android:text",
            method = "getText"
        )
    )
    object AuthEditBinding{
        @JvmStatic
        @BindingAdapter(value = ["android:textAttrChanged"])
        fun setListener(editText: AuthEditText, listener: InverseBindingListener?) {
            if (listener != null) {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                    }

                    override fun afterTextChanged(editable: Editable) {
                        listener.onChange()
                    }
                })
            }
        }

        @JvmStatic
        @BindingAdapter("android:text")
        fun setText(editText: AuthEditText, text: String?) {
            text?.let {
                if (it != editText.text) {
                    editText.text = it
                }
            }
        }
    }
}