package com.althreeus.socialnetwork.services

import com.althreeus.socialnetwork.api.SocialNetworkApiService

/**
 * Created by Alejandro on 20/03/2018.
 */
abstract class ServiceBase {

    val monitor:Object   = Object()

    val apiService by lazy {
       SocialNetworkApiService.create()
    }


}