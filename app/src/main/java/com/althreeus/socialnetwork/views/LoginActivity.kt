package com.althreeus.socialnetwork.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.althreeus.socialnetwork.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register.setOnClickListener(this)
        login.setOnClickListener { navToHome() }
    }

    private fun navToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
