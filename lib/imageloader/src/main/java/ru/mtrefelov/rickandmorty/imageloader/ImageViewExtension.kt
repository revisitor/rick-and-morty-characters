package ru.mtrefelov.rickandmorty.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: CharSequence) {
    Glide.with(context).load(url).into(this)
}