package com.mayburger.eatclone.ui.region

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.databinding.ItemLoadingBinding
import com.mayburger.eatclone.databinding.ItemRegionBinding
import com.mayburger.eatclone.model.RegionDataModel
import com.mayburger.eatclone.ui.base.BaseViewHolder


class SelectRegionAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data: MutableList<ItemRegionViewModel>
    private var mListener: Callback? = null
    private var isFailedLoadData = false
    private var isLoading = false

    companion object {
        const val VIEW_TYPE_NORMAL = 1
        const val VIEW_TYPE_LOADING = 2
    }

    init {
        this.data = ArrayList()
    }

    override fun getItemCount(): Int {
        return if (data.isNotEmpty()) {
            data.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (data.isNotEmpty()) {
            VIEW_TYPE_NORMAL
        } else
            VIEW_TYPE_LOADING
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val viewBinding = ItemRegionBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return RegionViewHolder(viewBinding)
            }
            else -> {
                val viewBinding = ItemLoadingBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return LoadingViewHolder(viewBinding)
            }
        }
    }


    fun addItems(data: ArrayList<ItemRegionViewModel>) {
        this.data.addAll(data)
        isFailedLoadData = false
        isLoading = false
        notifyDataSetChanged()
    }

    fun clearItems() {
        data.clear()
        notifyDataSetChanged()
    }

    fun setListener(listener: Callback) {
        this.mListener = listener
    }

    interface Callback {
        fun onSelectedItem(region: RegionDataModel)
    }

    inner class RegionViewHolder(private val mBinding: ItemRegionBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            mBinding.viewModel = data[position]
            mBinding.image.setOnClickListener {
                data[position].region?.let { it1 -> mListener?.onSelectedItem(it1) }
            }
            mBinding.executePendingBindings()
        }
    }

    inner class LoadingViewHolder(private val mBinding: ItemLoadingBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            mBinding.executePendingBindings()
        }
    }
}