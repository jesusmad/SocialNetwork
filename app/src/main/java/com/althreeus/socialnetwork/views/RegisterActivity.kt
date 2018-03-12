package com.althreeus.socialnetwork.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.althreeus.socialnetwork.R
import kotlinx.android.synthetic.main.login.view.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
