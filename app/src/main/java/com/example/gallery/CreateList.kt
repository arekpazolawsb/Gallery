package com.example.gallery
import java.io.Serializable

// Model zdjecia z id, opisem i oceną
class CreateList : java.io.Serializable {

    var description: String = ""
    var image_id :Int = 0
    var rating = 0.0f
}