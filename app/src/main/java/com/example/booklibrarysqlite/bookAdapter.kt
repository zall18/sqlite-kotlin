package com.example.booklibrarysqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import drawable.bookModel

class bookAdapter(private val context: Context, private val bookdata: List<bookModel>):BaseAdapter() {

    var layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return bookdata.size
    }

    override fun getItem(position: Int): Any {
        return bookdata[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemview = convertView ?: layoutInflater.inflate(R.layout.viewitem, null, false)

        var name = itemview.findViewById<TextView>(R.id.book_name_text)
        var author = itemview.findViewById<TextView>(R.id.author_text)
        var publisher = itemview.findViewById<TextView>(R.id.publisher_text)

        var data = getItem(position) as bookModel
        name.text = data.book_name
        author.text = data.author
        publisher.text = data.publisher

        return itemview
    }
}