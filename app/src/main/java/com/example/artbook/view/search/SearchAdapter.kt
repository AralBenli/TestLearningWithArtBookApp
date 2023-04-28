package com.example.artbook.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbook.databinding.SearchItemRowBinding
import javax.inject.Inject

/**
 * Created by AralBenli on 26.04.2023.
 */
class SearchAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var searchImageList: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    var clickArt: ((item: String) -> Unit)? = null


    inner class SearchViewHolder(private val binding: SearchItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artList: String) {
            with(binding) {
                val url = searchImageList[position]
                glide.load(url).into(searchRowImageView)

            }
            itemView.setOnClickListener {
                clickArt?.invoke(artList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            SearchItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int  = searchImageList.size


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentItem = searchImageList[position]
        holder.bind(currentItem)
    }


}