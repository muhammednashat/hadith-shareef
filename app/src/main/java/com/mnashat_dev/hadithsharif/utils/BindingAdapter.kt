   package com.mnashat_dev.hadithsharif.utils

   import android.widget.TextView
   import androidx.databinding.BindingAdapter
   import com.mnashat_dev.hadithsharif.data.models.Hadith


   @BindingAdapter("benefitOfHadith")
   fun TextView.bindTextOfBenefit(hadith:Hadith){
      text = hadith.benefit
   }
   @BindingAdapter("narratorOfHadith")
   fun TextView.bindTextOfNarrator(hadith:Hadith){
      text = hadith.narrator
   }


   @BindingAdapter("almohdathOfHadith")
   fun TextView.bindTextOfAlmohadath(hadith:Hadith){
      text = hadith.almahdath
   }
   @BindingAdapter("explanationOfHadith")
   fun TextView.bindTextOfExplanation(hadith:Hadith){
      text = hadith.explanation
   }

   @BindingAdapter("bodyOfHadith")
   fun TextView.bindBodyOfHadith(hadith:Hadith){
      text = hadith.body
   }