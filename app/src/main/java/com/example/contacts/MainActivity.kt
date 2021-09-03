package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.contacts.fragments.FragmentDetail
import com.example.contacts.fragments.FragmentList

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateTo(FragmentList(), "fragmentList")
    }

    override fun navigateTo(fragment: Fragment, name: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(name)
            .commit()
    }

    override fun navigateToTabletDetail(fragment: Fragment, name: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_detail_tablet, fragment)
            .commit()
    }

    override fun onBackPressed() {
        var currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (applicationContext?.resources?.configuration?.smallestScreenWidthDp!! >= 600) {
            currentFragment =
                supportFragmentManager.findFragmentById(R.id.container_detail_tablet)!!
        }
        if (currentFragment is FragmentDetail) {
            supportFragmentManager.popBackStack("fragmentList", 0)
        } else {
            finish()
        }
    }
}

interface Navigator {
    fun navigateTo(fragment: Fragment, name: String)
    fun navigateToTabletDetail(fragment: Fragment, name: String)
}