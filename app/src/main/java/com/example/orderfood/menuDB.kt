package com.example.orderfood

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class menuDB (context: Context,name: String = database,factory: SQLiteDatabase.CursorFactory? = null , version: Int = v):SQLiteOpenHelper(context, name, factory, version){
    companion object{
        private const val database = "mdatabase.db"
        private const val v = 1

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE menuTable( menu text PRIMARY KEY, price integer NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS menuTable")
        onCreate(db)
    }
}