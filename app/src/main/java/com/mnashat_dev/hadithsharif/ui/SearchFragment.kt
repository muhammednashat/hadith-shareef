package com.mnashat_dev.hadithsharif.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mnashat_dev.hadithsharif.R
import com.mnashat_dev.hadithsharif.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,container,false)

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
//                filterList(newText)
                return true
            }

        })

        return binding.root
    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        super.onViewCreated(view, savedInstanceState)
//        binding.lifecycleOwner = this
//        binding.recyclerviewSearch.adapter = itemsAdapter
//        viewModel.items.observe(viewLifecycleOwner, Observer {
//
//        })
//
//    }


//    private fun filterList(newText: String) {
//
//        var listOfItems = (viewModel.items.value)?.toMutableList()
//        Log.e("TAG","listOfItems $listOfItems")
//        if (newText == "") {
//            listOfItems?.clear()
//        }
//        listOfItems?.let {
//            listOfItems = listOfItems!!.filter { it.name!!.contains(newText) }.toMutableList()
//        }
//        if (!listOfItems.isNullOrEmpty()) {
//            binding.tvNoData.visibility = View.GONE
//            itemsAdapter.submitList(listOfItems)
//
//        } else {
//            binding.tvNoData.visibility = View.VISIBLE
//            itemsAdapter.submitList(listOfItems)
//        }
//
//
//    }

}