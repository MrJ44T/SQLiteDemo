package com.example.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SQLiteDatabase(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addBook(title: String, author: String, pages: Int) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_AUTHOR, author)
            put(COLUMN_PAGES, pages)
        }
        val result: Long = db.insert(TABLE_NAME, null, cv)
        if(result.toInt() == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(context, "Added Successfully!!!", Toast.LENGTH_LONG).show()
        }
    }

    fun readAllData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        var cursor:Cursor? = null
        if(db != null) {
            cursor = db.rawQuery(query, null)
        }

        return cursor
    }

    private companion object {
        const val DATABASE_NAME = "BOOK_LIBRARY"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "MY_LIBRARY"
        const val COLUMN_ID = "book_id"
        const val COLUMN_TITLE = "book_title"
        const val COLUMN_AUTHOR = "book_author"
        const val COLUMN_PAGES = "book_pages"
    }
}