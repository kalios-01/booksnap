package com.kaliostech.booksnap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class bookAdapter(private val mList: List<Book>) : RecyclerView.Adapter<bookAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.item_img.setImageURI(ItemsViewModel.imgpath)
        holder.item_whatilearn.text = ItemsViewModel.rv_bookname
        holder.item_bookname.text = ItemsViewModel.rv_whatilearn

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val item_img: ImageView = itemView.findViewById(R.id.item_img)
        val item_bookname: TextView = itemView.findViewById(R.id.item_bookname)
        val item_whatilearn: TextView = itemView.findViewById(R.id.item_whatilearn)
    }
}
