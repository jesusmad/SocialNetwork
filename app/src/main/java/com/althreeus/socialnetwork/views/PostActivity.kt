package com.althreeus.socialnetwork.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.adapter.PostsCustomAdapter
import com.althreeus.socialnetwork.model.Post
import com.althreeus.socialnetwork.model.Topic
import com.althreeus.socialnetwork.services.SocialNetworkService
import kotlinx.android.synthetic.main.activity_post.*
import org.jetbrains.anko.toast

class PostActivity : AppCompatActivity() {

        private lateinit var posts:ArrayList<Post>
        private lateinit var service:SocialNetworkService
        private lateinit var adapter:PostsCustomAdapter
        private lateinit var topic: Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        service = SocialNetworkService.instance
        topic = intent.getSerializableExtra("topic") as Topic
        posts = service.getPost(13)

        Log.d(HomeActivity.TAG, "POSTS: ${posts.count()}")
        Log.d(HomeActivity.TAG, "TOPIC: ${topic.name}, ${topic.url}, ID TOPIC: ${topic.id}, ID USER: ${topic.iduser}")

        loadAdapter()

        configureView()

    }

    private fun configureView() {

        tvTopicNamePost.text = topic.name
        tvTopicDatePost.text = topic.date
        tvTopicNickPost.text = topic.nick
        tvTopicTechPost.text = topic.nametechnology

        ivTopicLinkPost.setOnClickListener { openWebView() }

        btnSend.setOnClickListener { sendPost() }
    }

    private fun sendPost() {

        val txt = etTopicPost.text.toString()

        if (txt.isNotEmpty()) {

            val post = service.addPost(topic.id, topic.iduser, txt)
            posts.add(post!!)
            adapter.notifyDataSetChanged()

        } else {
            toast("Post cannot be empty!")
        }

    }

    private fun openWebView() {

    }

    private fun loadAdapter() {
        adapter = PostsCustomAdapter(this,R.layout.post_row, posts)
        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.adapter = adapter
    }
}
