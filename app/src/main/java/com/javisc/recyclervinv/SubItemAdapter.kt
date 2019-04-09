package com.javisc.recyclervinv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sub_item.view.*


class SubItemAdapter : ListAdapter<SubItem, SubItemAdapter.SubItemViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = inflater.inflate(R.layout.sub_item, parent, false)
        return SubItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: SubItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SubItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SubItem) = with(itemView) {
            val text = "${item.hourStart} to ${item.hourEnd}"
            textViewHour.text = text
            textViewTotalHours.text = (item.hourEnd - item.hourStart).toString()
        }
    }

    private class TaskDiffCallback : DiffUtil.ItemCallback<SubItem>() {

        override fun areItemsTheSame(
            oldItem: SubItem,
            newItem: SubItem
        ): Boolean = oldItem.hourStart == newItem.hourStart

        override fun areContentsTheSame(
            oldItem: SubItem,
            newItem: SubItem
        ): Boolean = oldItem == newItem

    }
}

