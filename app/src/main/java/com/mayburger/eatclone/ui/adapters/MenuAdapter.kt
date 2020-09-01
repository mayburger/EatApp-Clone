package com.mayburger.eatclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.databinding.ItemMenuBinding
import com.mayburger.eatclone.databinding.ItemMenuEmptyBinding
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMenuViewModel
import com.mayburger.eatclone.ui.base.BaseViewHolder


class MenuAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data: MutableList<ItemMenuViewModel>
    private var mListener: Callback? = null

    init {
        this.data = ArrayList()
    }

    companion object {
        const val VIEW_TYPE_NORMAL = 1
        const val VIEW_TYPE_EMPTY = 2
    }

    override fun getItemCount(): Int {
        return if (data.isNotEmpty()) {
            data.size
        } else {
            2
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (data.isNotEmpty()) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val viewBinding = ItemMenuBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MenuViewHolder(viewBinding)
            }
            VIEW_TYPE_EMPTY -> {
                val viewBinding = ItemMenuEmptyBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MenuEmptyViewHolder(viewBinding)
            }
            else -> {
                val viewBinding = ItemMenuBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MenuViewHolder(viewBinding)
            }
        }
    }

    fun addItems(data: ArrayList<ItemMenuViewModel>) {
        this.data.addAll(data)
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
        fun onSelectedItem(menu: MenuDataModel,position:Int)
    }

    inner class MenuEmptyViewHolder(private val mBinding: ItemMenuEmptyBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
        }
    }

    inner class MenuViewHolder(private val mBinding: ItemMenuBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                val viewModel = data[position]
                mBinding.viewModel = viewModel
                mBinding.root.setOnClickListener {
                    mListener?.onSelectedItem(data[position].data,position)
                }
            }
        }
    }
}