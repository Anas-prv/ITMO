package com.lection.lection_03

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val text = view.findViewById<TextView>(R.id.text_1)
    val image = view.findViewById<ImageView>(R.id.image_view)
    val red = view.context.getColor(R.color.red)
    val blue = view.context.getColor(R.color.blue)

    fun bind(number: Int, pos: Int) {
        text.text = "$number"
        if (pos % 2 == 0) {
            image.setBackgroundColor(blue)
        } else {
            image.setBackgroundColor(red)
        }
    }

}