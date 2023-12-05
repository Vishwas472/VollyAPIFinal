package com.example.apiapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.vollyapi.R
import com.example.vollyapi.response2Item

class APIAdapter(
    var context: Context,
    var result: List<response2Item>,
    var onclick: (Int) -> Unit
) : RecyclerView.Adapter<APIAdapter.MyviewHolder>() {
    class MyviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txttitle: TextView = itemView.findViewById(R.id.txttitle)
        var txtbody: TextView = itemView.findViewById(R.id.txtbody)
        var txtid: TextView = itemView.findViewById(R.id.txtid)
        var txtuserId: TextView = itemView.findViewById(R.id.txtuserId)
        var CardLayout: CardView = itemView.findViewById(R.id.CardLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.api_item_file, parent, false)
        return MyviewHolder(view)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {

        holder.txttitle.text = result[position].title.toString()
        holder.txtbody.text = result[position].body.toString()
        holder.txtid.text = result[position].id.toString()
        holder.txtuserId.text = result[position].userId.toString()
        holder.CardLayout.setOnClickListener {
            onclick.invoke(result[position].id!!.toInt())
        }
    }


    override fun getItemCount(): Int {
        return result.size
    }
}