package com.example.orderfood

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    var greentea = "綠茶"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_choice.setOnClickListener {
            startActivityForResult(Intent(this,Main2Activity::class.java),1)
        }
        btn_choice2.setOnClickListener {
            startActivityForResult(Intent(this,Main3Activity::class.java),2)
        }
        btn_sheetdb.setOnClickListener{
            startActivityForResult(Intent(this,Main4Activity::class.java),3)
        }
    }
/*
OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("text/plain");
RequestBody body = RequestBody.create(mediaType, "");
Request request = new Request.Builder()
  .url("https://sheetdb.io/api/v1/5qemd699oou25?data=[{\"drink\":\"奶茶\",\"sugar\":\"奶茶\",\"ice\":\"奶茶\",\"food\":\"奶茶\",\"egg\":\"奶茶\",\"cheese\":\"奶茶\"}]")
  .method("POST", body)
  .addHeader("Cookie", "__cfduid=de55877dc2fee0b4459aab6fb223b67f71593435376")
  .build();
Response response = client.newCall(request).execute();
 */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.let {
            if (requestCode==1 && resultCode == Activity.RESULT_OK){
                tv_meal.text = "飲料: ${it.getString("drink")}\n\n"+
                        "甜度: ${it.getString("sugar")}\n\n"+
                        "冰塊: ${it.getString("ice")}"

            }
            else if (requestCode==2 && resultCode == Activity.RESULT_OK){
                tv_meal2.text = "餐點: ${it.getString("food")}\n\n"+
                        "加蛋: ${it.getString("egg")}\n\n"+
                        "加起司: ${it.getString("cheese")}"
            }
        }
    }
}
