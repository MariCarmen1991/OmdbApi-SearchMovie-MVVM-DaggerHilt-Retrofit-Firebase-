package com.example.movieapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movieapp.R
import com.example.movieapp.data.model.Search
import com.squareup.picasso.Picasso

class FilmAdapter( private var filmList: List<Search> ): RecyclerView.Adapter<FilmAdapter.MyViewHolder>() {

    private lateinit var mListener: OnClickListener


    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnClickListener){

        mListener=listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_film_layout,parent,false)


        return MyViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = filmList[position]

        holder.filmName.text= currentItem.title
        holder.filmYear.text= currentItem.year
        Picasso.get()
            .load(currentItem.poster)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.image)

    }

    override fun getItemCount(): Int= filmList.size

    class MyViewHolder(itemView: View, listener: OnClickListener): RecyclerView.ViewHolder(itemView){
        var filmName=itemView.findViewById<TextView>(R.id.tw_titulo)
        var filmYear=itemView.findViewById<TextView>(R.id.tw_ano)
        var image= itemView.findViewById<ImageView>(R.id.iv_poster)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(bindingAdapterPosition)
            }
        }

    }





}