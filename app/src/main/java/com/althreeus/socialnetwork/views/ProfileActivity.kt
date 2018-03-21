package com.althreeus.socialnetwork.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.adapter.RepositoryCustomAdapter
import com.althreeus.socialnetwork.services.GitHubService
import com.althreeus.socialnetwork.services.SocialNetworkService
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val repositories = SocialNetworkService.instance.getRepositories("albertoperezs90")

        rvRepositories.layoutManager = LinearLayoutManager(this)
        rvRepositories.adapter = RepositoryCustomAdapter(this, R.layout.repositories_row, repositories)
    }
}
