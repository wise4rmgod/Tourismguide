package com.example.multikskills.tourismguide

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_details.*
import android.provider.MediaStore.Images.Media.getBitmap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import android.widget.Toast
import com.google.android.gms.location.places.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.location.places.ui.PlacePicker.getAttributions


class ViewDetails : AppCompatActivity() {
    private var geoDataClient: GeoDataClient?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_details)
        var idintent =intent.getStringExtra("id")
        var nameintent =intent.getStringExtra("name")
        var webintent =intent.getStringExtra("web")
        var address =intent.getStringExtra("address")
        var lat = intent.getDoubleExtra("lat",0.00)
        var lng = intent.getDoubleExtra("lng",0.00)

      //  holder.ratingBar.numStars = place.rating.toInt()

        name1.text=nameintent
        web.text=webintent
        addressbar.text=address



         mapview.setOnClickListener {
             var i= Intent(this,MapsActivity::class.java)
             i.putExtra("lat",lat)
             i.putExtra("lng",lng)
             startActivity(i)
         }

        val placeId = idintent
        val photoMetadataResponse = geoDataClient?.getPlacePhotos(placeId)
        photoMetadataResponse?.addOnCompleteListener(OnCompleteListener<PlacePhotoMetadataResponse> { task ->
            // Get the list of photos.
            val photos = task.result
            // Get the PlacePhotoMetadataBuffer (metadata for all of the photos).
            val photoMetadataBuffer = photos.photoMetadata
            // Get the first photo in the list.
            val photoMetadata = photoMetadataBuffer.get(0)
            // Get the attribution text.
            val attribution = photoMetadata.attributions
            // Get a full-size bitmap for the photo.
            val photoResponse = geoDataClient?.getPhoto(photoMetadata)
            photoResponse?.addOnCompleteListener(OnCompleteListener<PlacePhotoResponse> { task ->
                val photo = task.result
                val bitmap = photo.bitmap
                imageView2.setImageBitmap(bitmap)

            })
        })
    }
    }


