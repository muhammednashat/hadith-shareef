package com.mnashat_dev.hadithsharif.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mnashat_dev.hadithsharif.data.models.Hadith

@BindingAdapter("benefitOfHadith")
fun TextView.bindTextOfBenefit(hadith:Hadith){
   text = hadith.benefit
}