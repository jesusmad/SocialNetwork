package com.althreeus.socialnetwork.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.adapter.PostsCustomAdapter
import com.althreeus.socialnetwork.model.Post
import com.althreeus.socialnetwork.model.Topic
import com.althreeus.socialnetwork.services.SocialNetworkService
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {
        private lateinit var posts:List<Post>
        private lateinit var service:SocialNetworkService
        private lateinit var adapter:PostsCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        service = SocialNetworkService.instance
        val topic = intent.getSerializableExtra("topic") as Topic
        posts = service.getPost(topic.id)
        loadAdapter()

    }

    private fun loadAdapter() {
       adapter = PostsCustomAdapter(this,R.layout.post_row,posts)
        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.adapter = adapter
    }
}
