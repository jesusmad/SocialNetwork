package com.althreeus.socialnetwork.api

import android.database.Observable
import com.althreeus.socialnetwork.model.Result
import com.althreeus.socialnetwork.model.User
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