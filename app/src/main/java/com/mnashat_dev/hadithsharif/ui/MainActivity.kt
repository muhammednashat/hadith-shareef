package com.mnashat_dev.hadithsharif.ui

import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.mnashat_dev.hadithsharif.R
import com.mnashat_dev.hadithsharif.ui.allhadith.AllHadithFragment
import com.mnashat_dev.hadithsharif.ui.home_fragment.HomeFragment
import com.mnashat_dev.hadithsharif.ui.serch.SearchFragment
import com.mnashat_dev.hadithsharif.utils.Toaster

class MainActivity : AppCompatActivity() {

    private lateinit var appUpdateManager: AppUpdateManager
    private  lateinit var toaster: Toaster
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // To set gradiant color for status bar use this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            val background =
                resources.getDrawable(R.drawable.gradiant_color) //bg_gradient is your gradient.
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(android.R.color.transparent)
            window.setBackgroundDrawable(background)
        }

        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        appUpdateManager = AppUpdateManagerFactory.create(this)

        toaster = Toaster(this.applicationContext)
        checkUpdateAvailability()
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
    private fun checkUpdateAvailability() {
        val appUpdateInfoTask = appUpdateManager!!.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                try {
                    appUpdateManager!!.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        123
                    )
                } catch (e: SendIntentException) {
                    throw RuntimeException(e)
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()

        appUpdateManager
            ?.appUpdateInfo
            ?.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    try {
                        appUpdateManager!!.startUpdateFlowForResult(
                            appUpdateInfo,
                            AppUpdateType.IMMEDIATE,
                            this,
                            123
                        )
                    } catch (e: SendIntentException) {
                        throw java.lang.RuntimeException(e)
                    }
                }
            }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout,fragment)
            commit()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode != RESULT_OK) {
                toaster.makeToast("رجاءا القيام بتحديث التطبيق تفاديا لحدوث مشاكل")
            } else {
                toaster.makeToast("تم التحديث بنجاح")
            }
        }
    }

}