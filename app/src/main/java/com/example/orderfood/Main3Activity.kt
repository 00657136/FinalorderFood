package com.example.orderfood

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*
import okhttp3.*
import java.io.IOException

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        btn_send2.setOnClickListener {
            if (ed_food.length()<1){
                Toast.makeText(this,"請輸入名稱",Toast.LENGTH_SHORT).show()

            }
            else{
                var b = Bundle()
                b.putString("food",ed_food.text.toString())

                b.putString("egg",radioGroup3.findViewById<RadioButton>(radioGroup3.checkedRadioButtonId).text.toString())
                b.putString("cheese",radioGroup4.findViewById<RadioButton>(radioGroup4.checkedRadioButtonId).text.toString())

                setResult(Activity.RESULT_OK, Intent().putExtras(b))
                finish()
            }
        }
        btn_sheetdb3.setOnClickListener{
            if (ed_food.length()<1){
                Toast.makeText(this,"請輸入名稱",Toast.LENGTH_SHORT).show()

            }
            else {
                val body =
                    RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "")
                val req = Request.Builder()
                    .url(
                        "https://sheetdb.io/api/v1/ssa9l1020yp3b?data=[{\"food\":\"" + ed_food.text.toString() + "\",\"egg\":\"" + radioGroup3.findViewById<RadioButton>(
                            radioGroup3.checkedRadioButtonId
                        ).text.toString() + "\",\"cheese\":\"" + radioGroup4.findViewById<RadioButton>(
                            radioGroup4.checkedRadioButtonId
                        ).text.toString() + "\"}]"
                    )
                    .post(body).build()
                OkHttpClient().newCall(req).enqueue(object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        // val json = response.body!!.string()

                        val responseStr = response!!.body()!!.string()
                        println(responseStr)//可以看到有沒有成功
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")

                    }
                })
            }
        }
    }
}
