//package com.mnashat_dev.hadithsharif
//
//package com.example.dawaai.ui.mainScreenFragment
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import com.example.dawaai.R
//import com.example.dawaai.data.models.Item
//import com.example.dawaai.databinding.FragmentMainScreenBinding
//import com.google.android.gms.ads.*
//import com.google.android.gms.ads.rewarded.RewardedAd
//import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
//
//const val AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917"
//
//class MainScreenFragment : Fragment() {
//
//    private lateinit var binding: FragmentMainScreenBinding
//    private lateinit var viewModel: MainScreenViewModel
//    private var TAG = "MainScreenFragment"
//    private var rewardedAd: RewardedAd? = null
//    private var isLoading = false
//    private lateinit var itemsAdapter: ItemsAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View {
//
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
//        val application = requireNotNull(this.activity).application
//        val viewModelFactory = MainScreenViewModelFactory(application)
//        viewModel = ViewModelProvider(this, viewModelFactory)[MainScreenViewModel::class.java]
//
//        MobileAds.initialize(requireActivity()) {}
//        loadRewardedAd()
//
//        itemsAdapter = ItemsAdapter(ItemsListener {
//            showRewardedVideo(it)
//        })
//
//        binding.searchView.setOnQueryTextListener(object :
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                filterList(newText)
//                return true
//            }
//
//        })
//
//        binding.viewModel = viewModel
//        return binding.root
//    }
//
//    private fun filterList(newText: String) {
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
//
//
//
//
//    private fun showRewardedVideo(item: Item) {
//        if (rewardedAd != null) {
//            rewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
//                override fun onAdDismissedFullScreenContent() {
//                    findNavController().navigate(
//                        MainScreenFragmentDirections.actionMainScreenFragmentToDetailsFragment(
//                            item
//                        )
//                    )
//                    Log.d(TAG, "Ad was dismissed.")
//                    rewardedAd = null
//                    loadRewardedAd()
//                }
//
//                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
//                    Log.d(TAG, "Ad failed to show.")
//                    rewardedAd = null
//                }
//
//                override fun onAdShowedFullScreenContent() {
//                    Log.d(TAG, "Ad showed fullscreen content.")
//                }
//            }
//
//            rewardedAd?.show(requireActivity(), OnUserEarnedRewardListener { rewardItem ->
//                val rewardAmount = rewardItem.amount
//                val rewardType = rewardItem.type
//                Log.d(TAG, "User earned the reward.")
//            })
//        }
//        findNavController().navigate(
//            MainScreenFragmentDirections.actionMainScreenFragmentToDetailsFragment(
//                item
//            )
//        )
//    }
//
//
//}