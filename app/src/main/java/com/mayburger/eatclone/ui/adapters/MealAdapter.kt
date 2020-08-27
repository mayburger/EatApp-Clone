package com.mayburger.eatclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.databinding.ItemMealBinding
import com.mayburger.eatclone.databinding.ItemMealEmptyBinding
import com.mayburger.eatclone.model.MealDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemMealViewModel
import com.mayburger.eatclone.ui.base.BaseViewHolder


class MealAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data: MutableList<ItemMealViewModel>
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
                val viewBinding = ItemMealBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MealViewHolder(viewBinding)
            }
            VIEW_TYPE_EMPTY -> {
                val viewBinding = ItemMealEmptyBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MealEmptyViewHolder(viewBinding)
            }
            else -> {
                val viewBinding = ItemMealBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                MealViewHolder(viewBinding)
            }
        }
    }


    fun addItems(data: ArrayList<ItemMealViewModel>) {
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
        fun onSelectedItem(restaurant: MealDataModel)
    }

    inner class MealEmptyViewHolder(private val mBinding: ItemMealEmptyBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
        }
    }

    inner class MealViewHolder(private val mBinding: ItemMealBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                val viewModel = data[position]
                viewModel.navigator = object:
                    ItemMealViewModel.Callback {
                    override fun onClickItem() {
                        mListener?.onSelectedItem(data[position].data)
                    }
                }
                mBinding.viewModel = viewModel
            }
        }
    }
}