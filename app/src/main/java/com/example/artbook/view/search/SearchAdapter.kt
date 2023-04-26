package com.example.artbook.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbook.databinding.SearchItemRowBinding
import com.example.artbook.local.entity.ArtModel
import javax.inject.Inject

/**
 * Created by AralBenli on 26.04.2023.
 */
class SearchAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val searchImageList: ArrayList<ArtModel> = arrayListOf()
    var clickArt: ((item: ArtModel) -> Unit)? = null


    inner class SearchViewHolder(private val binding: SearchItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artList: ArtModel) {
            with(binding) {
                glide.load(artList.imageUrl).into(searchRowImageView)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            SearchItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int = searchImageList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentItem = searchImageList[position]
        holder.bind(currentItem)
    }


}