package com.example.gallery

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var createList : ArrayList<CreateList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // lista zdjęć, jeżeli nie ma w saved instance state ich wtedy wywołuje metodę prepareData
        createList = when {
            savedInstanceState != null -> savedInstanceState.getSerializable("createList") as ArrayList<CreateList>
            else -> prepareData()
        }

        var layoutManager = GridLayoutManager(this, 2)
        recycler_view.layoutManager = layoutManager

        // adapter z lista zdjec
        var myAdapter= MyAdapter(this, createList)

        // ustawienie adaptera do naszego asdaptera z lista zdjec
        recycler_view.adapter = myAdapter
        recycler_view.addItemDecoration(GridSpacingItemDecoration(2,50,false))

        recycler_view.setHasFixedSize(false)
    }

    // przy zapisywaniu instance statu wrzuca listę z modelami zdjęć
    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        outState.putSerializable("createList", createList)
    }

    override fun onResume() {
        super.onResume()
        recycler_view.adapter!!.notifyDataSetChanged()
    }

    // zwraca zdjecia korzystając z image_desc i image_ids. Zdjęcia znajdują się w res/drawable
    private fun prepareData(): ArrayList<CreateList> {
        var theimage = ArrayList<CreateList>()
        for(i in 0 until  image_desc.size){
            var createList = CreateList()
            createList.description = image_desc[i]
            createList.image_id = image_ids[i]
            theimage.add(createList)
        }
        return theimage
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                // szuka zdjęcia, które było wcześniej wybrane i ustawia na nowo rating
                var elem = createList.find { it.image_id == data!!.getIntExtra("imageID",0)}
                elem!!.rating = data!!.getFloatExtra("imageRating",0.0f)

                // sortowanie zdjęc po ratingu
                createList.sortBy { 5 - it.rating }
            }
        }
    }

    // lista z opisami zdjęć
    private var image_desc : ArrayList<String> = arrayListOf("img1","img2","img3","img4","img5","img6","img7","img8","img9")

    // lista z id zdjęć
    private var image_ids : IntArray = intArrayOf(R.drawable.img1, R.drawable.img2, R.drawable.img3,R.drawable.img4,
        R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9)
}
