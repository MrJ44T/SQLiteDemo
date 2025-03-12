package com.example.sqlitedemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    private var context: Context,
    private var bookId: ArrayList<String>,
    private var bookTitle: ArrayList<String>,
    private var bookAuthor: ArrayList<String>,
    private var bookPagesCount: ArrayList<String>
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookId.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bookId.text = bookId[position]
        holder.bookTitle.text = bookTitle[position]
        holder.bookAuthor.text = bookAuthor[position]
        holder.bookPagesCount.text = bookPagesCount[position]

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookId: TextView
        var bookTitle: TextView
        var bookAuthor: TextView
        var bookPagesCount: TextView

        init {
            bookId = itemView.findViewById(R.id.bookId)
            bookTitle = itemView.findViewById(R.id.bookTitle)
            bookAuthor = itemView.findViewById(R.id.bookAuthor)
            bookPagesCount = itemView.findViewById(R.id.bookPagesCount)
        }
    }
}