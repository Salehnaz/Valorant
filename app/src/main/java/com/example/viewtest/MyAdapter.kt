package com.example.viewtest

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso

private const val post_desc: Int=0
private const val post_Image: Int=1

class MyAdapter(val context: Context, val userList:List<MyDataItem>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private lateinit var mListner :onItemClickListner


    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner : onItemClickListner){
        mListner = listner
    }



    class DescViewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(myData: MyData){

        }
    }
    class ImageViewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(myData: MyData){

        }
    }


    class ViewHolder(itemView:View,listner: onItemClickListner):RecyclerView.ViewHolder(itemView) {
      //  var userID : TextView
        var title : TextView
        var imgview:ImageView

        init {
          //  userID = itemView.findViewById(R.id.order_id)
            title = itemView.findViewById(R.id.second_fname_id)
            imgview=itemView.findViewById(R.id.second_image_id)

            itemView.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == post_desc){
            var itemView = LayoutInflater.from(context).inflate(R.layout.row_data_ad,parent,false)
            return ViewHolder(itemView,mListner)
        }else
        {
            var itemView = LayoutInflater.from(context).inflate(R.layout.row_items,parent,false)
            return ViewHolder(itemView,mListner)

        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.userID.text=userList[position].id.toString()
        holder.title.text=userList[position].firstname.toString()

        //Picasso.get().load(userList[position].Image).into(holder.imgview)
        Glide.with(context).load(userList[position].Image).into(holder.imgview)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (userList[position].id % 2==0){
            post_desc
        }else{
            post_Image
        }
    }
}