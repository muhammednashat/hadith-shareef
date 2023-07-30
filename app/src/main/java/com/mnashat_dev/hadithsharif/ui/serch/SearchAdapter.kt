package com.mnashat_dev.hadithsharif.ui.serch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mnashat_dev.hadithsharif.data.models.Hadith
import com.mnashat_dev.hadithsharif.databinding.ItemViewSerchFragmentBinding


class SearchAdapter(private val clickListener: SearchListener) :
    ListAdapter<Hadith, SearchAdapter.SearchViewHolder>(ItemDiffUtil()) {



    class SearchViewHolder private constructor(val binding: ItemViewSerchFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            hadith: Hadith, clickListener: SearchListener
        ) {

            binding.hadith = hadith
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): SearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewSerchFragmentBinding.inflate(layoutInflater, parent, false)

                return SearchViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}


class ItemDiffUtil() : DiffUtil.ItemCallback<Hadith>() {
    override fun areItemsTheSame(oldHadith: Hadith, newHadith: Hadith): Boolean {
        return oldHadith.id == newHadith.id
    }

    override fun areContentsTheSame(oldHadith: Hadith, newHadith: Hadith): Boolean {
        return oldHadith == newHadith
    }

}

class SearchListener(private val clickListener: (hadith: Hadith) -> Unit) {

    fun onClick(hadith: Hadith) = clickListener(hadith)
}