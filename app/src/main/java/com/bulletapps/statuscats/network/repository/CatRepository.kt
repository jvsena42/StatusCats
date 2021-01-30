package com.bulletapps.statuscats.network.repository

import android.content.Context
import com.bulletapps.statuscats.model.CatModel
import com.bulletapps.statuscats.model.ErrorModel
import com.bulletapps.statuscats.R
import com.bulletapps.statuscats.network.RetrofitClient
import com.bulletapps.statuscats.network.listener.APIListener
import com.bulletapps.statuscats.network.services.CatService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatRepository(val context: Context) {
    private val mRemote = RetrofitClient.createService(CatService::class.java)

    fun getPhoto(id:String, listener: APIListener<CatModel>){
        val call: Call<CatModel> = mRemote.getCats(id)
        call.enqueue(object : Callback<CatModel>{
            override fun onResponse(call: Call<CatModel>, response: Response<CatModel>) {

                if (!response.isSuccessful){ //Se a resposta for diferente de sucesso
                    //Converter o Json para String
                    val message = Gson().fromJson(response.errorBody()?.string(), ErrorModel::class.java)
                    listener.onFailure(message.error)
                }
                response.body()?.let { listener.onSuccess(it) }
            }

            override fun onFailure(call: Call<CatModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }


}