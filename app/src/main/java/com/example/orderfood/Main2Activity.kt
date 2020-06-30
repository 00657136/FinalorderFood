package com.example.orderfood

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import okhttp3.*
import java.io.IOException

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_send.setOnClickListener {
            if (ed_drink.length()<1){
                Toast.makeText(this,"請輸入名稱",Toast.LENGTH_SHORT).show()

            }
            else{
                var b = Bundle()
                b.putString("drink",ed_drink.text.toString())

                b.putString("sugar",radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString())
                b.putString("ice",radioGroup2.findViewById<RadioButton>(radioGroup2.checkedRadioButtonId).text.toString())

                setResult(Activity.RESULT_OK, Intent().putExtras(b))
                finish()
            }
        }
        btn_sheetdb2.setOnClickListener{
            if (ed_drink.length()<1){
                Toast.makeText(this,"請輸入名稱",Toast.LENGTH_SHORT).show()

            }
            else {
                val body =
                    RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "")
                val req = Request.Builder()
                    .url(
                        "https://sheetdb.io/api/v1/ssa9l1020yp3b?data=[{\"drink\":\"" + ed_drink.text.toString() + "\",\"sugar\":\"" + radioGroup.findViewById<RadioButton>(
                            radioGroup.checkedRadioButtonId
                        ).text.toString() + "\",\"ice\":\"" + radioGroup2.findViewById<RadioButton>(
                            radioGroup2.checkedRadioButtonId
                        ).text.toString() + "\"}]"
                    )
                    .post(body).build()
                OkHttpClient().newCall(req).enqueue(object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        // val json = response.body!!.string()

                        val responseStr = response!!.body()!!.string()
                        println(responseStr)
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")

                    }
                })
            }
        }
    }
}
