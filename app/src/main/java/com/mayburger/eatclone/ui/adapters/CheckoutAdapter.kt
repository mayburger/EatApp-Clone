package com.mayburger.eatclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.databinding.ItemCheckoutBinding
import com.mayburger.eatclone.model.MenuDataModel
import com.mayburger.eatclone.ui.adapters.viewmodels.ItemCheckoutViewModel
import com.mayburger.eatclone.ui.base.BaseViewHolder


class CheckoutAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data: MutableList<ItemCheckoutViewModel>
    private var mListener: Callback? = null

    init {
        this.data = ArrayList()
    }

    companion object {
        const val VIEW_TYPE_NORMAL = 1
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewBinding = ItemCheckoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CheckoutViewHolder(viewBinding)
    }

    fun addItems(data: ArrayList<ItemCheckoutViewModel>) {
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
        fun onSelectedItem(menu: MenuDataModel, position: Int)
        fun onQuantityChanged(menu: MenuDataModel)
    }

    inner class CheckoutViewHolder(private val mBinding: ItemCheckoutBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            if (data.isNotEmpty()) {
                val viewModel = data[position]
                val menu = data[position].data
                viewModel.navigator= object: ItemCheckoutViewModel.Callback{
                    override fun onQuantityChanged(quantity: Int) {
                        menu.quantity = quantity
                        mListener?.onQuantityChanged(menu)
                    }
                }
                mBinding.viewModel = viewModel
            }
        }
    }
}