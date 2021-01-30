package com.bulletapps.statuscats.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*Classe para receber a mensagem de erro*/
data class ErrorModel(
    @SerializedName("error")
    val error: String
)