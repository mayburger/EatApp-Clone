package com.mayburger.eatclone.ui.base

import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBSDFragment<T : ViewDataBinding, V : BaseViewModel<*>> :
    BottomSheetDialogFragment(),BaseNavigatorFragment{

    var baseActivity: BaseActivity<*, *>? = null
        private set
    private var mRootView: View? = null
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.baseActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding!!.root
        return mRootView
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun finishActivity() {
        baseActivity?.finishActivity()
    }

    override fun hideLoading() {
        baseActivity?.hideLoading()
    }


    override fun showLoading() {
        baseActivity?.showLoading()
    }

    override fun onError(message: String?) {
        baseActivity?.onError(message)
    }

    override fun showSnackBar(message: String) {
        baseActivity?.showSnackBar(message)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.apply {
            setVariable(bindingVariable, mViewModel)
            executePendingBindings()
        }
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun getContentResolver(): ContentResolver? = baseActivity?.contentResolver
}