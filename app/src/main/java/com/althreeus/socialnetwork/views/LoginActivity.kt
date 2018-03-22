package com.althreeus.socialnetwork.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.model.User
import com.althreeus.socialnetwork.services.GitHubService
import com.althreeus.socialnetwork.services.SocialNetworkService
import com.google.gson.Gson
import kotlinx.android.synthetic.main.login.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences("github_app", Context.MODE_PRIVATE)
        editor = preferences.edit()

        val possibleUser = preferences.getString("user", "")
        if (possibleUser != ""){
            val user = Gson().fromJson(possibleUser, User::class.java)
            SocialNetworkService.userLogged = user

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener(this)
        register.setOnClickListener(this)
        chkbxSession.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            login.id -> startHomeActivity()
            register.id -> startRegisterActivity()
            chkbxSession.id -> updatePreferencesData(chkbxSession.isChecked)
        }
    }

    private fun updatePreferencesData(checked: Boolean) {
        if (checked){
            val name = etLoginUsername.text.toString()
            val password = etLoginPassword.text.toString()
            val user = SocialNetworkService.instance.getUserByNickPassword(name, password)
            if (user != null){
                val githubUser = GitHubService.instance.getGithubUser(user.nickgit)
                user.avatar_url = githubUser!!.avatar_url

                Log.d("Guardando usuario", user.toString())
                val userJson = Gson().toJson(user)
                editor.putString("user", userJson)
                editor.commit()
            }
        }else {
            Log.d("Eliminando usuario", "eliminado")
            editor.remove("user")
            editor.commit()
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
