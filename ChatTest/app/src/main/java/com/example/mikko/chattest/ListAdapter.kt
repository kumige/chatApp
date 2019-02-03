/*
Mikko Romo
1606176

A custom adapter that updates the list of messages.
*/
package com.example.mikko.chattest


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.message_cell.view.*
import com.example.mikko.chattest.R.layout.message_cell

class ListAdapter(var context: Context): BaseAdapter(){

    var items = mutableListOf<ChatMessage>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: LinearLayout

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(message_cell, null) as LinearLayout
        }else {
            view = convertView as LinearLayout
        }
        view.msg_cell.text = items[position].message
        view.timestamp.text = items[position].timestamp
        view.username.text = items[position].username

        return view
    }

    fun add(d: ChatMessage){
        items.add(d)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}