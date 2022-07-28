package com.example.homework5

import retrofit2.Call
import com.example.homework5.model.MemeList
import com.example.homework5.model.Meme
import com.example.homework5.model.MemeObject
import retrofit2.http.GET

interface RetrofitService {

    @GET("get_memes")
    fun getJson(): Call<MemeObject>
}

