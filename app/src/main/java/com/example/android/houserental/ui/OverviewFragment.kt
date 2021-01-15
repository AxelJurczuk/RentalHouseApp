package com.example.android.houserental.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.houserental.data.Result
import com.example.android.houserental.databinding.FragmentOverviewBinding
import com.example.android.houserental.ui.adapter.ItemHouseAdapter
import com.example.android.houserental.domain.model.House
import com.example.android.houserental.viewmodel.HouseViewModel

class OverviewFragment : Fragment(), ItemHouseAdapter.OnItemClick {

    private lateinit var binding: FragmentOverviewBinding
    private val viewModel: HouseViewModel by viewModels()

    private lateinit var adapter: ItemHouseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val houseObserver = Observer<Result> {
            binding.progressBar.visibility= View.GONE
            when (it) {
                is Result.Success -> {
                    adapter.houseList = it.list
                    adapter.notifyDataSetChanged()
                    if (adapter.houseList.isEmpty()) {
                        binding.ivSearStateEmpty.visibility= View.VISIBLE
                        binding.recyclerView.visibility= View.GONE
                    }else{
                        binding.ivSearStateEmpty.visibility= View.GONE
                        binding.recyclerView.visibility= View.VISIBLE
                    }

                }
                is Result.Failure -> Toast.makeText(
                    requireContext(),
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.getHouseListLiveData().observe(this, houseObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOverviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        val searchView = binding.searchView

        adapter = ItemHouseAdapter(requireContext(), this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                viewModel.setFilteredHouseListLiveData(query)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setFilteredHouseListLiveData(newText)
                return false
            }
        })

    }

    override fun onItemClickListener(position: Int) {
        val house = adapter.houseList[position]
        val action = OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(house)
        findNavController().navigate(action)
    }
}