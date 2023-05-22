package com.courage.instrumentlender.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.courage.instrumentlender.LoginFragment
import com.courage.instrumentlender.R
import com.courage.instrumentlender.fragment.CartFragment

class LoginActivity : AppCompatActivity() {
    lateinit var frameLayout : FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        frameLayout=findViewById(R.id.frameLayout)
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, LoginFragment())
        transaction.commit()
    }
}