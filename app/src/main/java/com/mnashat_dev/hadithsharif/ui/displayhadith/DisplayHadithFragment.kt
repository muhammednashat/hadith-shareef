package com.mnashat_dev.hadithsharif.ui.displayhadith

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mnashat_dev.hadithsharif.R
import com.mnashat_dev.hadithsharif.data.models.Hadith
import com.mnashat_dev.hadithsharif.databinding.FragmentDisplayHadithBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DisplayHadithFragment : Fragment() {

    private val viewModel: DisplayHadithViewModel by viewModel()
    private lateinit var binding: FragmentDisplayHadithBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_display_hadith,container,false)

        val hadith = arguments?.getParcelable<Hadith>("hadith")

        binding.tvBodyHadith.text = hadith?.body
        binding.tvBenefit.text =  hadith?.benefit
        binding.tvAlmahdath.text =  hadith?.almahdath
        binding.tvNarrator.text =  hadith?.narrator
        binding.tvExplanation.text =  hadith?.explanation

        return binding.root
    }

}