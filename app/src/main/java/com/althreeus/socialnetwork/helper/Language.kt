package com.althreeus.socialnetwork.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.althreeus.socialnetwork.views.MainActivity
import java.util.*

@Suppress("DEPRECATION")
/**
 * Created by alberto on 23/02/2018.
 */
class Language {

    fun change(from: Context, language: String){
        val locale = Locale(language)
        val metrics = from.resources.displayMetrics
        val config = from.resources.configuration
        config.locale = locale
        from.resources.updateConfiguration(config, metrics)

        val activity: Activity = from as Activity
        val refresh = Intent(from, activity::class.java)

        activity.finish()
        activity.startActivity(refresh)
    }
}