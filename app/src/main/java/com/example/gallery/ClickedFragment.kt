package com.example.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_clicked.*

class ClickedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_clicked, container, false)
    }

    // ustawia opis i rating zdjęcia we fragmencie
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity?.intent != null) {
            description.text = activity?.intent!!.getStringExtra("imageDescription")
            ratingBar.rating = activity?.intent!!.getFloatExtra("imageRating", 0.0f)
        }
    }
}