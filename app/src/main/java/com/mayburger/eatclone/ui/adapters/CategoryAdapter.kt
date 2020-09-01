package com.mayburger.eatclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.databinding.ItemCategoryBinding
import com.mayburger.eatclone.databinding.ItemCategoryEmptyBinding
import com.mayburger.eatclone.model.CategoryDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemCategoryViewModel
import com.mayburger.eatclone.ui.base.BaseViewHolder


class CategoryAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data: MutableList<ItemCategoryViewModel>
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
                val viewBinding = ItemCategoryBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                CategoryViewHolder(viewBinding)
            }
            VIEW_TYPE_EMPTY -> {
                val viewBinding = ItemCategoryEmptyBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                CategoryEmptyViewHolder(viewBinding)
            }
            else -> {
                val viewBinding = ItemCategoryBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                CategoryViewHolder(viewBinding)
            }
        }
    }


    fun addItems(data: ArrayList<ItemCategoryViewModel>) {
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
        fun onSelectedItem(restaurant: CategoryDataModel)
    }

    inner class CategoryEmptyViewHolder(private val mBinding: ItemCategoryEmptyBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
        }
    }

    inner class CategoryViewHolder(private val mBinding: ItemCategoryBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                val viewModel = data[position]
                mBinding.root.setOnClickListener { mListener?.onSelectedItem(data[position].data) }
                mBinding.viewModel = viewModel
            }
        }
    }
}