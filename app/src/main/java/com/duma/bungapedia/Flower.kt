package com.duma.bungapedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flower(
    val name: String,
    val description: String,
    val photo: Int
):Parcelable
