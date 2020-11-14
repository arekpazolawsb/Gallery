package com.example.gallery

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class MyAdapter(var context: Context, var galleryList: ArrayList<CreateList>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.cell_layout,p0,false)
        return ViewHolder(view)
    }

    // zwraca liczbę zdjęć
    override fun getItemCount(): Int {
        return galleryList.count()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: MyAdapter.ViewHolder, i: Int) {
        // ustawienie wartości w viewHolderze
        viewHolder.smallRating.rating = galleryList[i].rating.toFloat()
        viewHolder.img.setImageResource(galleryList[i].image_id)
        viewHolder.description = galleryList[i].description

        viewHolder.img.setOnTouchListener(object: View.OnTouchListener {

            // to co sie dzieje po kliknięciu
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                v?.performClick()

                if(event!!.action == MotionEvent.ACTION_UP) {
                    val ac = context as Activity
                    var myIntent = Intent(ac, ClickedActivity::class.java)

                    // wrzuca do myIntenta informacje o id zdjęcia, ratingu i opisie
                    myIntent.putExtra("imageID",galleryList[i].image_id)
                    myIntent.putExtra("imageRating",galleryList[i].rating)
                    myIntent.putExtra("imageDescription",galleryList[i].description)
                    ac.startActivityForResult(myIntent,1)
                }
                return true
            }
        })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var smallRating : RatingBar = itemView.findViewById(R.id.smallRatingBar) as RatingBar
        //private set
        var img : ImageView = itemView.findViewById(R.id.img) as ImageView
        //private set
        lateinit var description : String
    }

}
