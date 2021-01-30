package com.bulletapps.statuscats.network.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bulletapps.statuscats.R
import com.bulletapps.statuscats.model.ErrorModel
import com.bulletapps.statuscats.network.RetrofitClient
import com.bulletapps.statuscats.network.listener.APIListener
import com.bulletapps.statuscats.network.services.CatService
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatRepository(val context: Context) {
    private val mRemote = RetrofitClient.createService(CatService::class.java)

    fun getPhoto(id: String, listener: APIListener<Bitmap>){
        val call: Call<ResponseBody> = mRemote.getCats(id)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (!response.isSuccessful) { //Se a resposta for diferente de sucesso
                    //Converter o Json para String
                    val message = Gson().fromJson(
                        response.errorBody()?.string(),
                        ErrorModel::class.java
                    )
                    listener.onFailure(message.error)
                } else {
                    val bm = BitmapFactory.decodeStream(response.body()!!.byteStream())
                    response.body()?.let { listener.onSuccess(bm) }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }


}