package com.mayburger.eatclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.ui.base.BaseHorizontalViewHolder
import com.mayburger.eatclone.ui.base.BaseHorizontalViewModel

class HorizontalSelectionAdapter<T : BaseHorizontalViewModel?> :
    RecyclerView.Adapter<BaseHorizontalViewHolder<*>>() {

    private var data: java.util.ArrayList<out T>? = null
    private var mListener: Callback<T>? = null
    var selected:Int? = 0

    fun setItems(data: java.util.ArrayList<out T>?) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return data!![position]!!.layoutId()
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHorizontalViewHolder<*> {
        val bind = DataBindingUtil.bind<ViewDataBinding>(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
        return BaseHorizontalViewHolder(bind)
    }

    fun setListener(listener: Callback<T>) {
        this.mListener = listener
    }

    interface Callback<T>{
        fun onSelectedItem(position: Int,item:T)
    }

    override fun onBindViewHolder(holder: BaseHorizontalViewHolder<*>, position: Int) {
        val model = data!![position]
        holder.binding.setVariable(BR.viewModel, model)
        holder.binding.root.setOnClickListener {
            data?.get(position)?.let { it1 -> mListener?.onSelectedItem(position, it1) }
            selected = position
            notifyDataSetChanged()
        }
        holder.binding.executePendingBindings()
    }
}