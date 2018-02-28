package com.althreeus.socialnetwork.api

import android.database.Observable
import com.althreeus.socialnetwork.model.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Alejandro on 28/02/2018.
 */
interface SocialNetworkApiService {


    @GET("users")
    fun getUsers():Observable<Result>

    @GET("topics")
    fun getTopics():Observable<Result>

    @GET("posts/{idTopic}")
    fun getPosts(
     @Path("idTopic")
     idTopic: Int

    ):Observable<Result>

    @GET("technologys")
    fun getTechnologies():Observable<Result>

    @GET("categorys")
    fun getCategories():Observable<Result>

    @GET("user/{idUser}")
    fun getUser(
            @Path("idUser")
            idUser: Int

    ):Observable<Result>

    @GET("topic/{idTopic}")
    fun getTopic(
            @Path("idTopic")
            idTopic: Int

    ):Observable<Result>


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