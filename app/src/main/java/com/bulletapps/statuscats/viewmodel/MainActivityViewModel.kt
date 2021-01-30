package com.bulletapps.statuscats.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bulletapps.statuscats.databinding.ActivityMainBinding
import com.bulletapps.statuscats.model.CatModel
import com.bulletapps.statuscats.network.listener.APIListener
import com.bulletapps.statuscats.network.listener.ValidationListener
import com.bulletapps.statuscats.network.repository.CatRepository
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import java.io.File
import kotlin.random.Random

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val mCatRepository = CatRepository(application)
    lateinit var binding: ActivityMainBinding
    val codes = listOf("100","101","102","200","201","202","204","206","207","300","301","302","303","304","305","307","400","401","402","403","404","405",
                    "406","408","409","410","411","412","413","414","415","416","417","418","420","421","422","423","424","425","426","429","431","444","450",
                    "451","499","500","501","502","503","504","506","507","508","509","510","511","599")

    private val mCatPhoto =  MutableLiveData<ValidationListener>()
    val catPhoto: LiveData<ValidationListener> = mCatPhoto

    fun getPhoto(){
        val index = Random.nextInt(0,58)
        mCatRepository.getPhoto(codes[index],object: APIListener<Bitmap>{
            override fun onSuccess(model: Bitmap) {
//                mCatPhoto.value = ValidationListener("",model,true)
                binding.ivBack.setImageBitmap(model)
                binding.tvFront.text = codes[index]
            }

            override fun onFailure(str: String) {
                mCatPhoto.value = ValidationListener(str)
            }

        })
    }
}