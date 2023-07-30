package com.mnashat_dev.hadithsharif.ui.home_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mnashat_dev.hadithsharif.R
import com.mnashat_dev.hadithsharif.data.models.ItemForFirebase
import com.mnashat_dev.hadithsharif.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Items")

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeFragmentViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
      binding.tvBenefit.setOnClickListener{
          addDataToFirebase()
      }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    viewModel.item.observe(viewLifecycleOwner, Observer {

        it?.let {
            Log.e("TAG","item $it")
             binding.tvBenefit.text = it.benefit
             binding.tvAlmahdath.text = it.almahdath
             binding.tvNarrator.text = it.narrator
             binding.tvBodyHadith.text = it.body
             binding.tvExplanation.text = it.explanation
        }
    })
    }

    private fun addDataToFirebase( ) {

        Log.e("TAG" , "addDataToFirebase ")
        val item = ItemForFirebase(7,
            "نصيحة لمن عجز عن قيام الليل والصدقة",
        "(( لن يَسمَعَ بي أحدٌ مِن هذه الأمةِ ، ولا يهوديٌّ ، ولا نصرانيٌّ ، ثم لا يؤمِنُ بي ، إلا كان مِن أصحابِ النارِ ، قال : قلتُ : ما قال رسولُ اللهِ صلَّى اللهُ عليه وسلَّم شيئًا إلا كان في كتابِ اللهِ عزَّ وجلَّ ، قال : فوجَدتُ ومَن يَكفُرْ به منَ الأحزابِ فالنارُ مَوعِدُه ))",
        "على بن طالب"

            ,
        "الالبانى",
        "يُخبِرُ رسولُ اللهِ صلَّى اللهُ علَيه وسلَّم في هذا الحديثِ أنَّه: \"لا يؤمِنُ عبدٌ\"، أي: لا يَكونُ مؤمِنًا أصلًا؛ فهو نفيٌ لأصلِ الإيمانِ لا لِكَمالِه، ولا يُعَدُّ ما عِندَه مِن تصديقِ القلبِ وإيمانِه، \"حتَّى يُؤمِنَ\"، أي: يَكونَ مؤمِنًا، \"بأربَعٍ\"، أي: بأربَعةِ أشياءَ، وهي: \"يَشهَدُ\"، أي: يَعْلَمُ ويَعتَقِدُ بقَلبِه ويُظهِرُ بلِسانِه \"أنْ لا إلهَ إلَّا اللهُ\"، أي: إنَّه لا مَعبودَ بحَقٍّ إلَّا اللهُ سبحانَه، \"و\" يَعْلَمَ ويَعتَقِدَ أنِّي \"محمَّدٌ رسولُ اللهِ\" وخاتَمُ رسُلِ اللهِ، \"بعَثَني بالحقِّ\"، أي: يؤمنُ بالتَّوحيدِ والرِّسالةِ، \"ويؤمِنُ\" أيضًا \"بالموتِ\"، وأنَّه حقٌّ، وأنَّ الدُّنيا تَفْنى فلا يَبْقى منها شيءٌ؛ فهي دارُ فَناءٍ فلا تَبْقَى، وأنَّ الموتَ يَكونُ بأمرِ اللهِ تعالى وقدَرِه وأجَلِه، \"وبالبَعثِ\"، أي: بوُقوعِ البعثِ والإحياءِ، \"بعدَ الموت\"، وأنَّ اللهَ تعالى يَبعَثُ مَن في القبورِ ويُخرِجُهم مِنها للحشرِ والحسابِ، \"ويؤمِنُ\" أيضًا، \"بالقدَرِ\"، وهو ما قدَّره اللهُ تعالى على عِبادِه مِن خَيرٍ أو شرٍّ؛ فمَن لم يُؤمِنْ بواحدٍ مِن هذه الأربعةِ لم يَكُنْ مُؤمِنًا.\n" +
                "والإيمانُ باللهِ سُبحانه وتَعالى وبمُلائكتِه وبكُتبِه وبرُسلِه وباليومِ الآخِرِ وبالقدَرِ خيرِه وشرِّه، هي أركانُ الإيمانِ؛ فلا يكونُ العبدُ مؤمِنًا حتَّى يؤمِنَ بهذه الأركانِ السِّتَّةِ، وذِكْرُ هذه الأربعةِ هنا لا يَنفي أهمِّيَّةَ الإيمانِ بالملائكةِ والكتُبِ المنزَّلةِ على الرُّسلِ؛ لأنَّ مَن آمَن باللهِ وبرَسولِه محمَّدٍ آمَن بكلِّ ما أخبَر به النَّبيُّ صلَّى اللهُ علَيه وسلَّم وبكلِّ ما جاء في كِتابِ اللهِ، فاستكمَل كلَّ أركانِ الإيمانِ.\n" +
                "وفي الحديثِ: أنَّ الإيمانَ باللهِ تعالى وبرَسولِه والإيمانَ بقدَرِ اللهِ تعالى خَيرِه وشرِّه، رُكنٌ لا يَصِحُّ الإيمانُ إلَّا به، وكذلك الإيمانُ بالموتِ وبالبَعثِ والنُّشورِ بعدَه.\n" +
                " ",false
                )
        val itemId = databaseReference.push().key!!
        databaseReference.child(itemId).setValue(item)
            .addOnCompleteListener{
                Toast.makeText(this.requireActivity() ,"Don", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(requireActivity() ,"Don not", Toast.LENGTH_SHORT).show()
            }

    }

}