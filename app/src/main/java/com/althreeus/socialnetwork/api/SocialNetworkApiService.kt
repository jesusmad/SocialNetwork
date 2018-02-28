package com.althreeus.socialnetwork.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alejandro on 28/02/2018.
 */
interface SocialNetworkApiService {








    companion object {
        fun create(): SocialNetworkApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://api.althreeus.com/")
                    .build()
            return retrofit.create(SocialNetworkApiService::class.java)
        }
    }
}