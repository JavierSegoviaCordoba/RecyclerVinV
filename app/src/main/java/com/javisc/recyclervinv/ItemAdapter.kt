package com.javisc.recyclervinv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*


class ItemAdapter() :
    ListAdapter<Item, ItemAdapter.ItemViewHolder>(TaskDiffCallback()) {

    var onClick: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = inflater.inflate(R.layout.item, parent, false)
        return ItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) = with(itemView) {
            setOnClickListener { onClick?.invoke(item) }

            textViewDay.text = item.day.toString()

            recyclerViewSubItem.layoutManager = LinearLayoutManager(itemView.context)
            val subItemAdapter = SubItemAdapter()
            subItemAdapter.submitList(item.subItemList)
            recyclerViewSubItem.adapter = subItemAdapter
            recyclerViewSubItem.isLayoutFrozen = true
        }
    }

    private class TaskDiffCallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.day == newItem.day

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

    }
}