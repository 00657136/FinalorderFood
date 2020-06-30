package com.example.orderfood

import android.content.AbstractThreadedSyncAdapter
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main4.*
import java.lang.Exception


class Main4Activity : AppCompatActivity() {

    private lateinit var dbrw : SQLiteDatabase
    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        dbrw = menuDB(this).writableDatabase
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        listView.adapter = adapter

        btn_search.setOnClickListener {
            val c = dbrw.rawQuery(if (ed_menu.length()<1) "SELECT * FROM menuTable" else "SELECT * FROM menuTable WHERE menu LIKE '${ed_menu.text}'",null)

            c.moveToFirst()
            items.clear()
            Toast.makeText(this,"共有${c.count}樣商品",Toast.LENGTH_SHORT).show()
            for (i in 0 until c.count){
                items.add("餐點名稱:\t${c.getString(1)}\t\t\t\t價格:\t${c.getString(2)}元")
                c.moveToNext()
            }
            c.close()
            adapter.notifyDataSetChanged()

        }

        btn_add.setOnClickListener {
            if (ed_menu.length()<1 || ed_price.length()<1)
                Toast.makeText(this,"想新增餐點，不可以沒填資料!",Toast.LENGTH_SHORT).show()
            else
                try {
                    dbrw.execSQL("INSERT INTO menuTable(menu, price) VALUES(?,?)",
                        arrayOf<Any?>(ed_menu.text.toString(), ed_price.text.toString())
                    )
                    Toast.makeText(this,"新增餐點:${ed_menu.text}    價格:${ed_price.text}元",Toast.LENGTH_SHORT).show()
                    ed_menu.setText("")
                    ed_price.setText("")
                }catch (e: Exception){
                    Toast.makeText(this,"新增失敗:$e",Toast.LENGTH_LONG).show()
                }
        }
        btn_update.setOnClickListener {
            if (ed_menu.length()<1 || ed_price.length()<1)
                Toast.makeText(this,"想修改餐點，不可以沒填資料!",Toast.LENGTH_SHORT).show()
            else
                try{
                    dbrw.execSQL("UPDATE menuTable SET price = ${ed_price.text} WHERE menu LIKE '${ed_menu.text}'")
                    Toast.makeText(this,"更新餐點:${ed_menu.text}    價格:${ed_price.text}元",Toast.LENGTH_SHORT).show()
                    ed_menu.setText("")
                    ed_price.setText("")
                }catch (e: Exception){
                    Toast.makeText(this,"更新失敗:$e",Toast.LENGTH_LONG).show()
                }
        }

        btn_delete.setOnClickListener {
            if (ed_menu.length()<1 || ed_price.length()<1)
                Toast.makeText(this,"想刪除餐點，不可以沒填資料!",Toast.LENGTH_SHORT).show()
            else
                try {
                    dbrw.execSQL("DELETE FROM menuTable WHERE menu LIKE '${ed_menu.text}'")
                    Toast.makeText(this,"刪除餐點:${ed_menu.text}",Toast.LENGTH_SHORT).show()
                    ed_menu.setText("")
                    ed_price.setText("")
                }catch (e: Exception){
                    Toast.makeText(this,"刪除失敗:$e",Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbrw.close()
    }


}
