package com.sunjoolee.sparta_applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunjoolee.sparta_applemarket.databinding.RecyclerViewItemBinding

interface ItemClick{
    fun onClick(view:View, position:Int)
}

class MyAdapter(private val dataSet: Array<Post>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var itemClick:ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = RecyclerViewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.apply {
            itemView.setOnClickListener {
                itemClick?.onClick(it, position)
            }

            with(dataSet[position]) {
                imageView.setImageResource(imageID)
                imageView.clipToOutline = true

                titleTextView.text = title
                locationTextView.text = location
                priceTextView.text = price.toString() + "원"

                commentsTextView.text = commentCnt.toString()
                heartsTextView.text = heartCnt.toString()
            }
        }
    }

    inner class MyHolder(val binding: RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        val imageView :ImageView
        val titleTextView:TextView
        val locationTextView:TextView
        val priceTextView:TextView

        val commentsTextView:TextView
        val heartImageView:ImageView
        val heartsTextView:TextView

        init{
            imageView = binding.itemIvImage
            titleTextView = binding.itemTvTitle
            locationTextView = binding.itemTvLocation
            priceTextView = binding.itemTvPrice

            commentsTextView = binding.itemTvComments
            heartImageView = binding.itemIvHeart
            heartsTextView = binding.itemTvHeart
        }
    }
}