package com.althreeus.socialnetwork.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.services.SocialNetworkService
import kotlinx.android.synthetic.main.login.view.*
import kotlinx.android.synthetic.main.register.*
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

        etRegisterName.setText(username)
        etRegisterPassword.setText(password)

        registerAccept.setOnClickListener( {v -> registerUser()})
        registerClear.setOnClickListener {
            etRegisterName.setText("")
            etRegisterEmail.setText("")
            etRegisterPassword.setText("")
            etGithubUser.setText("")
        }

    }

    private fun registerUser() {
        val name = etRegisterName.text.toString()
        val password = etRegisterPassword.text.toString()
        val email = etRegisterEmail.text.toString()
        val gitUser = etGithubUser.text.toString()
        val user = SocialNetworkService.instance.registerUser(name, password, email, gitUser)

        if (user != null){
            toast("Te has registrado correctamente")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
