package com.mihodihasan.marvelsuperheroes.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import java.math.BigInteger
import java.security.MessageDigest

fun Context.toast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}