package com.mnashat_dev.hadithsharif.ui.allhadith

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mnashat_dev.hadithsharif.data.models.Hadith
import com.mnashat_dev.hadithsharif.databinding.ItemRecyclerAllHadithBinding


class AllHadithAdapter(private val clickListener: HadithListener) :
    ListAdapter<Hadith, AllHadithAdapter.AllHadithViewHolder>(ItemDiffUtil()) {


    class AllHadithViewHolder private constructor(val binding: ItemRecyclerAllHadithBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            hadith: Hadith, clickListener: HadithListener
        ) {

            binding.hadith = hadith
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): AllHadithViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecyclerAllHadithBinding.inflate(layoutInflater, parent, false)

                return AllHadithViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHadithViewHolder {
        return AllHadithViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AllHadithViewHolder, position: Int) {
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

class HadithListener(private val clickListener: (hadith: Hadith) -> Unit) {

    fun onClick(hadith: Hadith) = clickListener(hadith)
}