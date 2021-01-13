package com.example.android.houserental.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.houserental.R
import com.example.android.houserental.databinding.FragmentInformationBinding


class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentInformationBinding.inflate(layoutInflater)


        return binding.root
    }

}