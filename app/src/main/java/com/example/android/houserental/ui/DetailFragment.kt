package com.example.android.houserental.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.android.houserental.R
import com.example.android.houserental.databinding.FragmentDetailBinding
import com.example.android.houserental.domain.model.House
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args:DetailFragmentArgs by navArgs()
    private lateinit var house:House

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(layoutInflater)

        house = args.houseDetail
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
        binding.tvPrice.text= house.price.toString()
        binding.tvDescription.text= house.description
    }
}