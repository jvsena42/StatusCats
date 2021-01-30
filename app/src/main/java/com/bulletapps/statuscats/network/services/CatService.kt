package com.bulletapps.statuscats.network.services

import android.graphics.Bitmap
import com.bulletapps.statuscats.model.CatModel
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface CatService {

    @Headers("Content-Type: image/png; charset=binary")
    @GET("{id}")
    fun getCats(@Path("id")id:String): Call<ResponseBody>

    /*@GET("")
    fun getCats(@Query("userId")userId:String): Call<File>*/
}