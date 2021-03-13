package com.orti.shoppingtodo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orti.shoppingtodo.databinding.RecyclerviewItemBinding
import java.text.FieldPosition

class ItemAdapter : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemsComparator()) {

    private lateinit var onItemClick: ItemAction

    interface ItemAction {
        fun onClick(getAdapterPosition: FieldPosition)
    }

    fun setItemOnClickAction(itemOnClickAction: ItemAction?) {
        onItemClick = itemOnClickAction!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ItemViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.itemName.text = item.name
            binding.itemPrice.text = item.price

        }

        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val binding = RecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ItemViewHolder(binding)
            }
        }
    }

    class ItemsComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            if (!oldItem.name.equals(newItem.name) ||
                !oldItem.price.equals(newItem.price)
            )
                return false

            return oldItem.name.equals(newItem.name) &&
                    oldItem.price.equals(newItem.price)
        }
    }
}