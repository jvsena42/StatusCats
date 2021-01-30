package com.bulletapps.statuscats.network.listener

import android.graphics.Bitmap
import com.bulletapps.statuscats.model.CatModel
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import java.io.File

/*Retorna boolean com sucesso ou falha e a mensagem de erro*/

data class ValidationListener(
    var message: String = "",
    var model: Bitmap? = null,
    var success: Boolean = false
)