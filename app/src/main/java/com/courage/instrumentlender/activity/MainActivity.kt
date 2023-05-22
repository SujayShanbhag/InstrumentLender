package com.courage.instrumentlender.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.courage.instrumentlender.R
import com.courage.instrumentlender.fragment.AddFragment
import com.courage.instrumentlender.fragment.CartFragment
import com.courage.instrumentlender.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView=findViewById(R.id.navbar)

        if (intent!=null && intent.getIntExtra("instrument_id",-1)!=-1) {
            val instrumentId=intent.getIntExtra("instrument_id",-1)
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, CartFragment(instrumentId))
            transaction.commit()
        }
        else {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, HomeFragment())
            transaction.commit()
        }
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
                    transaction.replace(R.id.frameLayout, CartFragment(-1))
                    transaction.commit()
                }
            }
            return@setOnItemSelectedListener true
        }

    }
}