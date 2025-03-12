package com.example.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    private lateinit var bookTitle: EditText
    private lateinit var bookAuthor: EditText
    private lateinit var bookPagesCount: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        bookTitle = findViewById(R.id.bookTitle)
        bookAuthor = findViewById(R.id.bookAuthor)
        bookPagesCount = findViewById(R.id.bookPagesCount)
        addButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val db = SQLiteDatabase(this@AddActivity)
            db.addBook(bookTitle.text.toString().trim(),
            bookAuthor.text.toString().trim(),
            bookPagesCount.text.toString().trim().toInt())
        }
    }
}