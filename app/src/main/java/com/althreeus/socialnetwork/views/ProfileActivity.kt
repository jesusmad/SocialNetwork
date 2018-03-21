package com.althreeus.socialnetwork.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.adapter.RepositoryCustomAdapter
import com.althreeus.socialnetwork.model.GithubUser
import com.althreeus.socialnetwork.model.User
import com.althreeus.socialnetwork.services.SocialNetworkService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.nav_header_home.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = intent.getSerializableExtra("githubUser") as GithubUser

        tvNameRepositories.setText(user.login)
        Picasso.with(this).load(user.avatar_url).into(ivAvatarHome)
        tvRepositories.setText(user.login)

        val repositories = SocialNetworkService.instance.getRepositories(user.login)

        rvRepositories.layoutManager = LinearLayoutManager(this)
        rvRepositories.adapter = RepositoryCustomAdapter(this, R.layout.repositories_row, repositories)
    }
}
