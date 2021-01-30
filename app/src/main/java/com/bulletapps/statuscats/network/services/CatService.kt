package com.bulletapps.statuscats.network.services

import com.bulletapps.statuscats.CatModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CatService {
    @GET("{id}")
    fun getCats(@Path("id")id:String): Call<CatModel>
}