package com.example.myvideo.VideoRest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myvideo.R

class CustomAdapter(
    private var mListCategory: ArrayList<ItemViewVideos>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val mtitule: TextView = ItemView.findViewById(R.id.txtTitulo)
        val msubtitle: TextView = ItemView.findViewById(R.id.txtSubtitulo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_video, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mListCategory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = mListCategory[position]

        holder.mtitule.text = category.title
        holder.msubtitle.text = category.subtitle

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(category)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(selectedItem: ItemViewVideos)
    }

}
