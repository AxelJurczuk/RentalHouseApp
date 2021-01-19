package com.example.android.houserental.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.android.houserental.R
import com.example.android.houserental.databinding.FragmentDetailBinding
import com.example.android.houserental.domain.model.House
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentDetailBinding
    private val args:DetailFragmentArgs by navArgs()
    private lateinit var house:House
    private lateinit var map:GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(layoutInflater)

        house = args.houseDetail
        createMapFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load("https://intern.docker-dev.d-tt.nl/${house.image}")
            .fit()
            .centerCrop()
            .into(binding.ivHouseImage)
        binding.tvBathroomNumber.text=house.bathrooms.toString()
        binding.tvBedNumber.text= house.bedrooms.toString()
        binding.tvSize.text= house.size.toString()
        binding.tvPrice.text= "$${house.price}"
        binding.tvDistance.text=house.distance
        binding.tvDescription.text= house.description

    }
    private fun createMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            map = googleMap
            createMarker()
        }
    }

    private fun createMarker() {
        val coordinates = LatLng(house.latitude, house.longitude)
        val marker = MarkerOptions().position(coordinates)
        Log.d("coordinates", coordinates.toString())
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 15f),
            4000,
            null
        )
    }
}