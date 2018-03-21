package com.althreeus.socialnetwork.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.services.GitHubService
import com.althreeus.socialnetwork.services.SocialNetworkService
import kotlinx.android.synthetic.main.login.*
import org.jetbrains.anko.toast

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
            val githubUser = GitHubService.instance.getGithubUser(user.nickgit)
            user.avatar_url = githubUser!!.avatar_url

            SocialNetworkService.userLogged = user

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else {
            toast("Ese usuario no existe en nuestra base de datos")
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
