package com.example.recyclerview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val titleTextView = findViewById<TextView>(R.id.title_text_view)
        val descriptionTextView = findViewById<TextView>(R.id.description_text_view)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        titleTextView.text = title
        descriptionTextView.text = description
    }
}