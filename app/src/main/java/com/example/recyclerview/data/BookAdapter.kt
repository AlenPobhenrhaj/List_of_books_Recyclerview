package com.example.recyclerview.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.BookDetail

class BookAdapter(private var books: List<BookDetail>, private val onItemClick: (BookDetail) -> Unit) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        private val authorTextView: TextView = itemView.findViewById(R.id.author_text_view)

        fun bind(book: BookDetail) {
            titleTextView.text = book.title
            authorTextView.text = book.author
            itemView.setOnClickListener { onItemClick(book) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun setBooks(newBooks: List<BookDetail>) {
        books = newBooks
        notifyDataSetChanged()
    }
}