package com.bulletapps.statuscats.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bulletapps.statuscats.model.CatModel
import com.bulletapps.statuscats.network.listener.APIListener
import com.bulletapps.statuscats.network.listener.ValidationListener
import com.bulletapps.statuscats.network.repository.CatRepository

 class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val mCatRepository = CatRepository(application)

    private val mCatPhoto =  MutableLiveData<ValidationListener>()
    val catPhoto: LiveData<ValidationListener> = mCatPhoto

    fun getPhoto(id:String){
        mCatRepository.getPhoto(id,object: APIListener<CatModel>{
            override fun onSuccess(model: CatModel) {
                mCatPhoto.value = ValidationListener("",model,true)
            }

            override fun onFailure(str: String) {
                mCatPhoto.value = ValidationListener(str)
            }

        })
    }
}