package com.mnashat_dev.hadithsharif.ui.allhadith

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mnashat_dev.hadithsharif.R
import com.mnashat_dev.hadithsharif.databinding.FragmentAllHadithBinding
import com.mnashat_dev.hadithsharif.ui.home_fragment.HomeFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllHadithFragment : Fragment() {

    private lateinit var binding:FragmentAllHadithBinding

    private val viewModel: AllHadithViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_hadith,container,false)
        return binding.root
    }

}