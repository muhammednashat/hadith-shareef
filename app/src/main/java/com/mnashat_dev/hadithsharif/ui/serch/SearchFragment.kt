package com.mnashat_dev.hadithsharif.ui.serch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mnashat_dev.hadithsharif.R
import com.mnashat_dev.hadithsharif.data.models.Hadith
import com.mnashat_dev.hadithsharif.databinding.FragmentSearchBinding
import com.mnashat_dev.hadithsharif.ui.displayhadith.DisplayHadithFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return true
            }

        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        searchAdapter = SearchAdapter(SearchListener {
            openDisplayHadithFragment(it)

        })

        binding.recyclerviewSearch.adapter = searchAdapter
        viewModel.items.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", "listOfItems $it")
        })
    }


    private fun filterList(newText: String) {

        var listOfItems = (viewModel.items.value)?.toMutableList()
        Log.e("TAG", "listOfItems $listOfItems")
        if (newText == "") {
            listOfItems?.clear()
        }
        listOfItems?.let {
            listOfItems = listOfItems!!.filter { it.body!!.contains(newText) }.toMutableList()
        }
        if (!listOfItems.isNullOrEmpty()) {
            binding.tvNoData.visibility = View.GONE
            searchAdapter.submitList(listOfItems)

        } else {
            binding.tvNoData.visibility = View.VISIBLE
            searchAdapter.submitList(listOfItems)
        }


    }

    private fun openDisplayHadithFragment(hadith: Hadith) {

        activity?.let { it ->
            val bundle = Bundle()
            bundle.putParcelable("hadith", hadith)
            val displayHadithFragment = DisplayHadithFragment()
            displayHadithFragment.arguments = bundle

            it.supportFragmentManager.beginTransaction().addToBackStack(
                DisplayHadithFragment::class.java.name

            ).replace(R.id.frame_layout, displayHadithFragment).commit()
        }
    }


}