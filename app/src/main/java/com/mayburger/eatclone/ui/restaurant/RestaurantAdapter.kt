package com.mayburger.eatclone.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.databinding.ItemRestaurantGridBinding
import com.mayburger.eatclone.databinding.ItemRestaurantListBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseViewHolder


class RestaurantAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data: MutableList<ItemRestaurantViewModel>
    private var mListener: Callback? = null

    init {
        this.data = ArrayList()
    }

    var viewType = VIEW_TYPE_LIST

    companion object {
        const val VIEW_TYPE_LIST = 1
        const val VIEW_TYPE_GRID = 2
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    fun asGrid(){
        this.viewType = VIEW_TYPE_GRID
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_GRID -> {
                val viewBinding = ItemRestaurantGridBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                GridViewHolder(viewBinding)
            }
            else -> {
                val viewBinding = ItemRestaurantListBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                ListViewHolder(viewBinding)
            }
        }
    }


    fun addItems(data: ArrayList<ItemRestaurantViewModel>) {
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
        fun onSelectedItem(restaurant: RestaurantDataModel)
    }

    inner class GridViewHolder(private val mBinding: ItemRestaurantGridBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                mBinding.viewModel = data[position]
                mBinding.root.setOnClickListener {
                    mListener?.onSelectedItem(data[position].data)
                }
            }
        }
    }
    inner class ListViewHolder(private val mBinding: ItemRestaurantListBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                mBinding.viewModel = data[position]
                mBinding.root.setOnClickListener {
                    mListener?.onSelectedItem(data[position].data)
                }
            }
        }
    }
}