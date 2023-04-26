package com.example.artbook.view.art

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbook.databinding.ArtItemRowBinding
import com.example.artbook.local.entity.ArtModel
import javax.inject.Inject

/**
 * Created by AralBenli on 26.04.2023.
 */

class ArtRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {


    private val artList: ArrayList<ArtModel> = arrayListOf()
    var clickArt: ((item: ArtModel) -> Unit)? = null


    inner class ArtViewHolder(private val binding: ArtItemRowBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(artList: ArtModel){
            with(binding){
                artRowArtisTxt.text = "Artist Name: ${artList.artistName}"
                artRowNameTxt.text = "Name : ${artList.name}"
                artRowYearTxt.text = "Year : ${artList.year.toString()}"
                glide.load(artList.imageUrl).into(imageView)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val binding =
            ArtItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArtViewHolder(binding)
    }

    override fun getItemCount(): Int = artList.size

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val currentItem = artList[position]
        holder.bind(currentItem)
    }

    private val diffUtil = object : DiffUtil.ItemCallback<ArtModel>() {
        override fun areItemsTheSame(oldItem: ArtModel, newItem: ArtModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArtModel, newItem: ArtModel): Boolean {
            return oldItem == newItem
        }


    }

}