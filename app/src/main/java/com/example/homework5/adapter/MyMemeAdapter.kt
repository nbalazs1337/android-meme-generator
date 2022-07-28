package com.example.homework5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework5.R
import com.example.homework5.model.MemeList

class MyMemeAdapter(val context: Context, val memeList: MutableList<MemeList>) :
    RecyclerView.Adapter<MyMemeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMemeAdapter.MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var txt_name: TextView
        var txt_mesures: TextView
        var iv_like: ImageView
        var iv_dislike: ImageView
        var iv_share: ImageView
        var iv_rotate: ImageView


        init {
            image = itemView.findViewById(R.id.iv_photo)
            txt_name = itemView.findViewById(R.id.tv_Name)
            txt_mesures = itemView.findViewById(R.id.tv_widthAndHeight)
            iv_like = itemView.findViewById(R.id.iv_like)
            iv_dislike = itemView.findViewById(R.id.iv_dislike)
            iv_share = itemView.findViewById(R.id.iv_share)
            iv_rotate = itemView.findViewById(R.id.iv_rotate)
        }
    }

    override fun onBindViewHolder(holder: MyMemeAdapter.MyViewHolder, position: Int) {

        holder.iv_rotate.setImageResource(R.drawable.ic_rotate_left)
        holder.iv_like.setImageResource(R.drawable.ic_like)
        holder.iv_dislike.setImageResource(R.drawable.ic_dislike)
        holder.iv_share.setImageResource(R.drawable.ic_share)
        holder.txt_name.text = memeList[position].name
        holder.txt_mesures.text =
            memeList[position].height.toString() + " x " + memeList[position].width.toString()
        var myUrl = memeList[position].url

        Glide.with(context)
            .load(myUrl)
            .into(holder.image)

        holder.iv_rotate.setOnClickListener {
            holder.image.rotation = 90.0f

        }


    }

    override fun getItemCount(): Int {
        return memeList.size
    }
}
