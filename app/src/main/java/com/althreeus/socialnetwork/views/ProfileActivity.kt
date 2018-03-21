package com.althreeus.socialnetwork.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.adapter.RepositoryCustomAdapter
import com.althreeus.socialnetwork.model.User
import com.althreeus.socialnetwork.services.SocialNetworkService
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = intent.getSerializableExtra("githubUser") as User

        val repositories = SocialNetworkService.instance.getRepositories(user.nick)

        rvRepositories.layoutManager = LinearLayoutManager(this)
        rvRepositories.adapter = RepositoryCustomAdapter(this, R.layout.repositories_row, repositories)
    }
}
