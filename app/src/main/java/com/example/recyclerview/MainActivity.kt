package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.BookDetailActivity
import com.example.recyclerview.R
import com.example.recyclerview.data.BookAdapter
import com.example.recyclerview.data.BooksApi
import com.example.recyclerview.model.BookDetail
import com.example.testapp1.model.Books
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_list)

        adapter = BookAdapter(emptyList()) { book ->
            val intent = Intent(this@MainActivity, BookDetailActivity::class.java)
            intent.putExtra("title", book.title)
            intent.putExtra("description", book.description)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val response = BooksApi().getBestSellers()

            if (response.isSuccessful) {
                val books = response.body()?.results?.flatMap { it.book_details } ?: emptyList()
                adapter.setBooks(books)
            } else {
                Log.e("testing", "Failed to get best sellers")
            }
        }
    }

}