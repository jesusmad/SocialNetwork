package com.althreeus.socialnetwork.api

import rx.Observable
import com.althreeus.socialnetwork.model.GithubUser
import com.althreeus.socialnetwork.model.Repository
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Alejandro on 12/03/2018.
 */
interface GithubApiService {

    @GET("users/{idUser}")
    fun getTopic(
            @Path("idUser")
            idUser: Int

    ): Observable<ResponseBody>


    @GET("users/{nameUser}")
    fun getUser(
            @Path("nameUser")
            nick: String
    ): Observable<GithubUser>

    @GET("users/{username}/repos")
    fun getRepositories(
            @Path("username") username: String
    ): Observable<List<Repository>>


    companion object {
        fun create(): GithubApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()
            return retrofit.create(GithubApiService::class.java)
        }
    }
}