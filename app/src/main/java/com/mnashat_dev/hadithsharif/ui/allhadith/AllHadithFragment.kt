package com.mnashat_dev.hadithsharif.ui.allhadith

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
import com.mnashat_dev.hadithsharif.databinding.FragmentAllHadithBinding
import com.mnashat_dev.hadithsharif.ui.MainActivity
import com.mnashat_dev.hadithsharif.ui.displayhadith.DisplayHadithFragment
import com.mnashat_dev.hadithsharif.ui.home_fragment.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllHadithFragment : Fragment() {

    private lateinit var binding:FragmentAllHadithBinding
    private lateinit var allHadithAdapter: AllHadithAdapter
    private val viewModel: AllHadithViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        allHadithAdapter = AllHadithAdapter(HadithListener {
            openDisplayHadithFragment(it)

        })

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_hadith,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerview()

    }

    private fun openDisplayHadithFragment(hadith: Hadith){

        activity?.let { it ->
            val bundle = Bundle()
            bundle.putParcelable("hadith",hadith)
            val displayHadithFragment = DisplayHadithFragment()
            displayHadithFragment.arguments = bundle

            it.supportFragmentManager.beginTransaction().addToBackStack(
                DisplayHadithFragment::class.java.name

            ).replace(R.id.frame_layout, displayHadithFragment).commit()
        }
    }

    private fun setUpRecyclerview(){

        binding.recyclerviewAllHadith.adapter = allHadithAdapter
        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.e("TAG","item $it")
                allHadithAdapter.submitList(it)
            }
        })

    }

}