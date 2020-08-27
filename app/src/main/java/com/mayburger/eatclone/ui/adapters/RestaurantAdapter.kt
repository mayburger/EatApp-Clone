package com.mayburger.eatclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.databinding.*
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemRestaurantViewModel
import com.mayburger.eatclone.ui.base.BaseViewHolder
import com.mayburger.eatclone.util.ext.setOnSingleClickListener


class RestaurantAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data: MutableList<ItemRestaurantViewModel>
    private var mListener: Callback? = null

    init {
        this.data = ArrayList()
    }

    var viewType =
        VIEW_TYPE_LIST

    companion object {
        const val VIEW_TYPE_LIST = 1
        const val VIEW_TYPE_LIST_EMPTY = 2
        const val VIEW_TYPE_GRID = 3
        const val VIEW_TYPE_GRID_EMPTY = 4
        const val VIEW_TYPE_COLLECTION = 5
        const val VIEW_TYPE_COLLECTION_EMPTY = 6
        const val VIEW_TYPE_IMAGE = 7
        const val VIEW_TYPE_IMAGE_EMPTY = 8
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
        return when (viewType) {
            VIEW_TYPE_LIST -> {
                if (data.isNotEmpty()) {
                    VIEW_TYPE_LIST
                } else {
                    VIEW_TYPE_LIST_EMPTY
                }
            }
            VIEW_TYPE_COLLECTION->{
                if (data.isNotEmpty()) {
                    VIEW_TYPE_COLLECTION
                } else {
                    VIEW_TYPE_COLLECTION_EMPTY
                }
            }
            VIEW_TYPE_IMAGE->{
                if (data.isNotEmpty()) {
                    VIEW_TYPE_IMAGE
                } else {
                    VIEW_TYPE_IMAGE_EMPTY
                }
            }
            else -> {
                if (data.isNotEmpty()) {
                    VIEW_TYPE_GRID
                } else {
                    VIEW_TYPE_GRID_EMPTY
                }
            }
        }
    }

    fun asGrid() {
        this.viewType =
            VIEW_TYPE_GRID
        notifyDataSetChanged()
    }

    fun asCollection(){
        this.viewType = VIEW_TYPE_COLLECTION
        notifyDataSetChanged()
    }

    fun asImage(){
        this.viewType = VIEW_TYPE_IMAGE
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_GRID -> {
                val viewBinding = ItemRestaurantGridBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                GridViewHolder(viewBinding)
            }
            VIEW_TYPE_GRID_EMPTY -> {
                val viewBinding = ItemRestaurantGridEmptyBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                GridEmptyViewHolder(viewBinding)
            }
            VIEW_TYPE_COLLECTION->{
                val viewBinding = ItemRestaurantCollectionBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                CollectionViewHolder(viewBinding)
            }
            VIEW_TYPE_COLLECTION_EMPTY -> {
                val viewBinding = ItemRestaurantCollectionEmptyBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                CollectionEmptyViewHolder(viewBinding)
            }
            VIEW_TYPE_IMAGE->{
                val viewBinding = ItemRestaurantImageBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                ImageViewHolder(viewBinding)
            }
            VIEW_TYPE_IMAGE_EMPTY -> {
                val viewBinding = ItemRestaurantImageEmptyBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                ImageEmptyViewHolder(viewBinding)
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
                val viewModel = data[position]
                mBinding.root.setOnSingleClickListener {
                    mListener?.onSelectedItem(data[position].data)
                }
                mBinding.viewModel = viewModel
                mBinding.root.apply {
                    val animation = AlphaAnimation(0f, 1.0f)
                    animation.duration = 300
                    startAnimation(animation)
                }
            }
        }
    }
    inner class CollectionViewHolder(private val mBinding: ItemRestaurantCollectionBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                val viewModel = data[position]
                mBinding.root.setOnSingleClickListener {
                    mListener?.onSelectedItem(data[position].data)
                }
                mBinding.viewModel = viewModel
                mBinding.root.apply {
                    val animation = AlphaAnimation(0f, 1.0f)
                    animation.duration = 300
                    startAnimation(animation)
                }
            }
        }
    }

    inner class GridEmptyViewHolder(private val mBinding: ItemRestaurantGridEmptyBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
        }
    }
    inner class ImageViewHolder(private val mBinding: ItemRestaurantImageBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                val viewModel = data[position]
                mBinding.root.setOnSingleClickListener {
                    mListener?.onSelectedItem(data[position].data)
                }
                mBinding.viewModel = viewModel
                mBinding.root.apply {
                    val animation = AlphaAnimation(0f, 1.0f)
                    animation.duration = 300
                    startAnimation(animation)
                }
            }
        }
    }

    inner class ImageEmptyViewHolder(private val mBinding: ItemRestaurantImageEmptyBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
        }
    }

    inner class CollectionEmptyViewHolder(private val mBinding: ItemRestaurantCollectionEmptyBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
        }
    }


    inner class ListViewHolder(private val mBinding: ItemRestaurantListBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                val viewModel = data[position]
                mBinding.root.setOnSingleClickListener {
                    mListener?.onSelectedItem(data[position].data)
                }
                mBinding.viewModel = viewModel
            }
        }
    }
}