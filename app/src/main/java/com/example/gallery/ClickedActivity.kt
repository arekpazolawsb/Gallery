package com.example.gallery

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clicked.*
import kotlinx.android.synthetic.main.fragment_clicked.*

class ClickedActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clicked)
    }

    // do returnIntenta wrzuca informacje o id zdjęcia, opisie i ratingu, który mógł ulec zmianie
    override fun finish(){
        val returnIntent = Intent()
        returnIntent.putExtra("imageID", intent.getIntExtra("imageID", R.drawable.img1))
        returnIntent.putExtra("imageDescription", intent.getStringExtra("imageDescription"))
        returnIntent.putExtra("imageRating", descFragment.ratingBar.rating)

        setResult(RESULT_OK, returnIntent)
        super.finish()
    }

}