package com.example.homework5

//import com.example.homework5.adapter.MyMemeAdapter
//import com.example.homework5.common.Common

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5.adapter.MyMemeAdapter
import com.example.homework5.model.MemeObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.imgflip.com/"


class MainActivity : AppCompatActivity() {

    lateinit var myMemeAdapter: MyMemeAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        getMyData()

    }

    private fun getMyData() {

        //Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val request = retrofit.create(RetrofitService::class.java)
        val call: Call<MemeObject> = request.getJson()
        call.enqueue(object : Callback<MemeObject?> {
            override fun onResponse(call: Call<MemeObject?>?, response: Response<MemeObject?>) {
                val responseBody = response.body()!!
                val goodBody = responseBody.data.memes
                // Log.d("idk", "${responseBody}")
                //Log.d("idk", "error code "+response.code().toString());

                //Bind response to UI
                myMemeAdapter = MyMemeAdapter(baseContext, goodBody)
                myMemeAdapter.notifyDataSetChanged()
                val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                recyclerView.adapter = myMemeAdapter

            }

            override fun onFailure(call: Call<MemeObject?>?, t: Throwable) {
                d("MainActivity", "onFailure: " + t.message)

            }
        })
    }
}


