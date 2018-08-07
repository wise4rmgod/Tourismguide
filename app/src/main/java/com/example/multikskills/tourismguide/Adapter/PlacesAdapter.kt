package com.example.multikskills.tourismguide.Adapter

import android.content.Context
import android.os.Bundle
import com.google.android.gms.location.places.Place
import android.widget.RatingBar
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.multikskills.tourismguide.R

class PlacesAdapter (private val placesList: List<Place>, private val context: Context) : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return placesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.placeslist, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlacesAdapter.ViewHolder, position: Int) {
        val itemPos = position
        val place = placesList[position]
        holder.name.text = place.name
        Toast.makeText(context,place.name,Toast.LENGTH_SHORT).show()
      /**  holder.address.text = place.address
        holder.phone.text = place.phoneNumber
        if (place.websiteUri != null) {
            holder.website.text = place.websiteUri.toString()
        }

        if (place.rating > -1) {
            holder.ratingBar.numStars = place.rating.toInt()
        } else {
            holder.ratingBar.visibility = View.GONE
        }  **/

        holder.itemView.setOnClickListener {
            Toast.makeText(context,"its working",Toast.LENGTH_SHORT).show()
        }

      /**  holder.viewOnMap.setOnClickListener(object : View.OnClickListener() {
            fun onClick(view: View) {
                showOnMap(place)
            }
        }) **/

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView
     //   var address: TextView
      //  var phone: TextView
      //  var website: TextView
      //  var ratingBar: RatingBar

        init {

            name = view.findViewById(R.id.textview)
          //  address = view.findViewById(R.id.address)
          //  phone = view.findViewById(R.id.phone)
          //  website = view.findViewById(R.id.website)
          //  ratingBar = view.findViewById(R.id.rating)
        }
    }

  /**  private fun showOnMap(place: Place) {
        val fm = (context as CurrentLocationNearByPlacesActivity)
                .getSupportFragmentManager()

        val bundle = Bundle()
        bundle.putString("name", place.name as String)
        bundle.putString("address", place.address as String)
        bundle.putDouble("lat", place.latLng.latitude)
        bundle.putDouble("lng", place.latLng.longitude)

        val placeFragment = PlaceOnMapFragment()
        placeFragment.setArguments(bundle)

        fm.beginTransaction().replace(R.id.map_frame, placeFragment).commit()
    }  **/
}