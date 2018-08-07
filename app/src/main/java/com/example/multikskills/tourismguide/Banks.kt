package com.example.multikskills.tourismguide

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.multikskills.tourismguide.Adapter.PlacesAdapter
import com.google.android.gms.location.places.*


class Banks : AppCompatActivity() {
    val TAG = "CurrentLocNearByPlaces"
    private val LOC_REQ_CODE = 1

    lateinit var geoDataClient: GeoDataClient
    lateinit var placeDetectionClient: PlaceDetectionClient
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banks)

        recyclerView = findViewById<RecyclerView>(R.id.places_lst)

        val recyclerLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = recyclerLayoutManager

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                recyclerLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        placeDetectionClient = Places.getPlaceDetectionClient(this, null)

        getCurrentPlaceItems()

    }

    private fun getCurrentPlaceItems() {
        if (isLocationAccessPermitted()) {
            getCurrentPlaceData()
        } else {
            requestLocationAccessPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentPlaceData() {
        val placeResult = placeDetectionClient!!.getCurrentPlace(null)
        placeResult.addOnCompleteListener { task ->
          //  Log.d(FragmentActivity.TAG, "current location places info")
          //  var filters = ArrayList<String>()
           //    filters.add(Place.TYPE_BANK.toString())
           //   var placeFilter = PlaceFilter(false, filters)
            val placesList = ArrayList<Place>()
            val likelyPlaces = task.result
            for (placeLikelihood in likelyPlaces) {
                placesList.add(placeLikelihood.place.freeze())
            }
            likelyPlaces.release()

            val recyclerViewAdapter = PlacesAdapter(placesList,applicationContext
                     )
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    private fun isLocationAccessPermitted(): Boolean {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationAccessPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOC_REQ_CODE)
    }

    override  fun  onActivityResult( requestCode:Int, resultCode:Int, data:Intent )
    {
        if (requestCode == LOC_REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                getCurrentPlaceData()
            }
        }


    }
}
