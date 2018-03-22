package com.althreeus.socialnetwork.views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.althreeus.realtimedbfirebase.adapter.CustomAdapterTopics
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.adapter.ViewPagerAdapter
import com.althreeus.socialnetwork.model.Technology
import com.althreeus.socialnetwork.model.Topic
import com.althreeus.socialnetwork.model.User
import com.althreeus.socialnetwork.services.SocialNetworkService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import org.jetbrains.anko.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    companion object {
        val TAG = "*** JR ***"


        lateinit var adapterLatest: CustomAdapterTopics
        lateinit var adapterMyTopics: CustomAdapterTopics
    }


    private var topics: ArrayList<Topic> = ArrayList()
    private var mytopics: ArrayList<Topic> = ArrayList()

    private var technologies: ArrayList<Technology> = ArrayList()

    private lateinit var socialnetservice: SocialNetworkService

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        setupViewPager()
        tabs.setupWithViewPager(viewpager)

        socialnetservice = SocialNetworkService.instance
        user = SocialNetworkService.userLogged!!

        setupViewPager()
        tabs.setupWithViewPager(viewpager)

        loadInfoDrawer()

        loadtopics()
        loadtechnologies()

        adapterLatest = CustomAdapterTopics(this, R.layout.topic_row, topics)
        adapterMyTopics = CustomAdapterTopics(this, R.layout.mytopic_row, mytopics)


        fab.setOnClickListener { newTopic() }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.update -> {
                loadtopics()
                adapterLatest.notifyDataSetChanged()
                adapterMyTopics.notifyDataSetChanged()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
        return true
    }


    private fun loadInfoDrawer() {

        val ivDrawer = nav_view.getHeaderView(0).ivAvatarHome
        val tvEmailDrawer = nav_view.getHeaderView(0).tvEmailHomeMenu
        val tvNameDrawer = nav_view.getHeaderView(0).tvNameHomeMenu


        Picasso.with(this).load(user.avatar_url).into(ivDrawer)

        tvEmailDrawer.text = "${user.nick}@email.com"
        tvNameDrawer.text = user.nickgit

    }

    private fun loadtechnologies() {

        technologies = ArrayList(socialnetservice.getTechnologies())

    }

    private fun newTopic() {

        alert {
            title = "Add a Topic!"
            customView {
                verticalLayout {
                    //backgroundResource = R.drawable.background
                    lparams(width = wrapContent, height = wrapContent)

                    val etName = editText {
                        hint = "Name"
                        padding = dip(10)
                        setHighlightColor(Color.BLACK)
                        setTextSize(18f)
                    }

                    val etTechnology = editText {
                        hint = "Technology"
                        padding = dip(10)
                        setHighlightColor(Color.BLACK)
                        setTextSize(18f)
                    }

                    neutralPressed("Close") {  }

                    positiveButton("Save") {
                        if (etName.text.isEmpty() || etTechnology.text.isEmpty())
                            longToast("Topic not added. All fields are required")
                        else {

                            val techname = etTechnology.text.toString()
                            val tech = checkTech(techname)

                            val topic = socialnetservice.addTopic(user.id, tech!!.id, 2, etName.text.toString())!!

                            Log.d(TAG, "${topic.name}, ${topic.date}, Tech: ${topic.nametechnology}")

                            loadtopics()

                            adapterLatest.notifyDataSetChanged()
                            adapterMyTopics.notifyDataSetChanged()

                        }

                    }

                }
            }
        }.show()


    }

    private fun checkTech(techname: String): Technology? {

        technologies.forEach {
            Log.d(TAG, it.name)
            if (it.name == techname)
                return it
        }

        val tech = socialnetservice.addTechnology(techname)
        Log.d(TAG, "TECH FROM API: ${tech!!.name}")
        technologies.add(tech!!)

        return tech
    }

    private fun loadtopics() {

        topics.clear()
        mytopics.clear()

        val aux = socialnetservice.getTopics()

        aux.forEach {
            topics.add(it)
            if (it.iduser == user.id)
                mytopics.add(it)
            Log.d(TAG, "TOPIC ID: ${it.id} USER ID: ${it.iduser} CAT ID: ${it.idcategory} TECH ID: ${it.idtechnology} " +
                    "NAME: ${it.name} NICK: ${it.nick}")
        }

        topics.reverse()
        mytopics.reverse()

    }

    private fun setupViewPager() {

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LatestTopicsFragment(), "Latest")
        adapter.addFragment(MyTopicsFragment(), "Mine")

        viewpager.adapter = adapter

    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.nav_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_users -> {

            }

            R.id.nav_allrepos -> {

            }

            R.id.nav_settings -> {

            }

            R.id.nav_about -> {

            }

            R.id.nav_closesession -> {

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
