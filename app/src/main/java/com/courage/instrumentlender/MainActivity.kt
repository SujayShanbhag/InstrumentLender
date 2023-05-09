package com.courage.instrumentlender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.courage.instrumentlender.fragment.AccountFragment
import com.courage.instrumentlender.fragment.AddFragment
import com.courage.instrumentlender.fragment.CartFragment
import com.courage.instrumentlender.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView=findViewById(R.id.navbar)

        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, HomeFragment())
        //navigationView.setCheckedItem(R.id.navHome)
        transaction.commit()
        navigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHome -> {
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout, HomeFragment())
                    transaction.commit()
                }
                R.id.navAdd -> {
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout, AddFragment())
                    transaction.commit()
                }
                R.id.navShop -> {
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout, CartFragment())
                    transaction.commit()
                }
                R.id.navProfile -> {
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout, AccountFragment())
                    transaction.commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}