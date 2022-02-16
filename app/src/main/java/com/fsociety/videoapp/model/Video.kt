package com.fsociety.videoapp.model

data class Video(val title:String,val url:String, var likes:Int){
    constructor():this("","",0)
}
