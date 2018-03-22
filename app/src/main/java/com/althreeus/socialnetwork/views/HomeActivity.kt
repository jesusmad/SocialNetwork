package com.althreeus.socialnetwork.views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.althreeus.realtimedbfirebase.adapter.CustomAdapterTopics
import com.althreeus.socialnetwork.R
import com.althreeus.socialnetwork.model.Technology
import com.althreeus.socialnetwork.model.Topic
import com.althreeus.socialnetwork.model.User
import com.althreeus.socialnetwork.services.SocialNetworkService
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import org.jetbrains.anko.*
import java.util.*
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


        socialnetservice = SocialNetworkService.instance
        user = SocialNetworkService.userLogged!!

        setupViewPager()
        tabs.setupWithViewPager(viewpager)

        loadtopics()
        loadtechnologies()

        adapterLatest = CustomAdapterTopics(this, R.layout.topic_row, topics)
        adapterMyTopics = CustomAdapterTopics(this, R.layout.topic_row, mytopics)





        fab.setOnClickListener { newTopic() }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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
        }


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

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}
