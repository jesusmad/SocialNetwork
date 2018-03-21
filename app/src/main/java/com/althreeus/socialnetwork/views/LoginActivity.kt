package com.althreeus.socialnetwork.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.services.SocialNetworkService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            login.id -> startHomeActivity()
            register.id -> startRegisterActivity()
        }
    }

    private fun startHomeActivity() {
        val name = etLoginUsername.text.toString()
        val password = etLoginPassword.text.toString()
        val user = SocialNetworkService.instance.getUserByNickPassword(name, password)

        if (user != null){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startRegisterActivity() {
        val name = etLoginUsername.text.toString()
        val password = etLoginPassword.text.toString()
        val intent = Intent(this, RegisterActivity::class.java)
        intent.putExtra("username", name)
        intent.putExtra("password", password)
        startActivity(intent)
    }
}
