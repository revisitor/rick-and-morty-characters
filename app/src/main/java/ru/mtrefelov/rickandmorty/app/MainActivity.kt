package ru.mtrefelov.rickandmorty.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent("ru.mtrefelov.rickandmorty.people.open")
        startActivity(intent)
    }
}