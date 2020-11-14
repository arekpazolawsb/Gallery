package com.example.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_clicked.*
import kotlinx.android.synthetic.main.fragment_clicked2.*

class ClickedFragment2: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_clicked2, container, false)
    }

    // ustawia zdjęcie we fragmencie2
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity?.intent != null) {
            image.setImageResource(activity?.intent!!.getIntExtra("imageID",R.drawable.img1))
        }
    }
}