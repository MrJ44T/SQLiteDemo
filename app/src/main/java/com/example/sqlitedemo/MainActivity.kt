package com.example.sqlitedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var db:SQLiteDatabase
    private lateinit var bookId:ArrayList<String>
    private lateinit var bookTitle:ArrayList<String>
    private lateinit var bookAuthor:ArrayList<String>
    private lateinit var bookPagesCount:ArrayList<String>
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)
        db = SQLiteDatabase(this@MainActivity)
        bookId = ArrayList()
        bookTitle = ArrayList()
        bookAuthor = ArrayList()
        bookPagesCount = ArrayList()

        addButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }

        storeDataInArrays()
        myAdapter = MyAdapter(this@MainActivity, bookId, bookTitle, bookAuthor, bookPagesCount)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun storeDataInArrays() {
        val cursor = db.readAllData()

        if(cursor?.count == 0) {
            Toast.makeText(this@MainActivity, "No data", Toast.LENGTH_LONG).show()
        }
        else {
            while (cursor?.moveToNext() == true) {
                bookId.add(cursor.getString(0))
                bookTitle.add(cursor.getString(1))
                bookAuthor.add(cursor.getString(2))
                bookPagesCount.add(cursor.getString(3))
            }
        }
    }
}