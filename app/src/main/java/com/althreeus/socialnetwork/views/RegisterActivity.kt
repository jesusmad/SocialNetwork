package com.althreeus.socialnetwork.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import com.althreeus.socialnetwork.R
import kotlinx.android.synthetic.main.login.view.*
import kotlinx.android.synthetic.main.register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

        etRegisterName.setText(username)
        etRegisterPassword.setText(password)

    }

}
