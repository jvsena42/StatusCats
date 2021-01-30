package com.bulletapps.statuscats.network.listener

interface APIListener <T>{

    fun onSuccess(model: T)
    fun onFailure(str: String)

}