package com.sunjoolee.sparta_applemarket

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val ID:Int,
    val imageID:Int,
    val title: String,
    val body:String,
    val writer:String,
    val price:Int,
    val location:String,
    var commentCnt:Int = 0,
    var heartCnt:Int = 0
) :Parcelable {
}