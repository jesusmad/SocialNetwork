package com.althreeus.socialnetwork.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.services.GitHubService
import com.althreeus.socialnetwork.services.SocialNetworkService
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
        val githubUser = GitHubService.instance.getGithubUser(gitUser)

        if (githubUser != null){
            val user = SocialNetworkService.instance.registerUser(name, password, email, gitUser)

            if (user != null){
                user.avatar_url = githubUser.avatar_url

                SocialNetworkService.userLogged = user

                toast("Te has registrado correctamente")
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                toast("Ese usuario ya existe")
            }
        }else {
            toast("Ese usuario no existe en Github")
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
