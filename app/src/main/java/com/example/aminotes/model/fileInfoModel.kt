package com.example.aminotes.model

data class fileInfoModel(
    val name:String,
    val url:String
) {
    constructor():this("","")
}