package com.example.studentapp.model

data class Handphone(
    var id:String?,
    var brand:String?,
    var model:String?,
    var releaseyear:String?,
    var colors:List<String>,
    var specifications:HandphoneSpec?)

data class HandphoneSpec(
    var display: String?,
    var processor: String?,
    var camera: String?,
    var battery: String?
)