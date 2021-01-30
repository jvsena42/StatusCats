package com.bulletapps.statuscats.network.listener

import com.bulletapps.statuscats.model.CatModel

/*Retorna boolean com sucesso ou falha e a mensagem de erro*/

data class ValidationListener(
    var message: String = "",
    var model: CatModel? = null,
    var success: Boolean = false
)