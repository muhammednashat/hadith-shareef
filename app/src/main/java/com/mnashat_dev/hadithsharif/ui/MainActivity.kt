package com.mnashat_dev.hadithsharif.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mnashat_dev.hadithsharif.R
import com.mnashat_dev.hadithsharif.ui.allhadith.AllHadithFragment
import com.mnashat_dev.hadithsharif.ui.home_fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val allHadithFragment = AllHadithFragment()
        val favoriteFagment = FavoriteFragment()

        setCurrentFragment(homeFragment)
       findViewById<BottomNavigationView>(R.id.bottom_nav).setOnNavigationItemSelectedListener {
           when(it.itemId){
               R.id.menu_item_home -> setCurrentFragment(homeFragment)
               R.id.menu_item_all_hadith -> setCurrentFragment(allHadithFragment)
               R.id.menu_item_favorite -> setCurrentFragment(favoriteFagment)
               R.id.menu_item_search -> setCurrentFragment(searchFragment)
           }
           true
       }

    }


    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout,fragment)
            commit()
        }
    }
}