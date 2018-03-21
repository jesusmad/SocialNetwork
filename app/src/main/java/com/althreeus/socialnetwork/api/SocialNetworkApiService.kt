package com.althreeus.socialnetwork.api


import com.althreeus.socialnetwork.model.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable

/**
 * Created by Alejandro on 28/02/2018.
 */
interface SocialNetworkApiService {



    @GET("topics")
    fun getTopics():Observable<Response>

    @GET("posts/{idTopic}")
    fun getPosts(
     @Path("idTopic")
     idTopic: Int

    ):Observable<Response>

    @GET("technologys")
    fun getTechnologies():Observable<Response>

    @GET("categorys")
    fun getCategories():Observable<Response>

    @GET("user/{idUser}")
    fun getUser(
            @Path("idUser")
            idUser: Int

    ):Observable<Response>

    @GET("topic/{idTopic}")
    fun getTopic(
            @Path("idTopic")
            idTopic: Int

    ):Observable<Response>

    @GET("topics/technology/{idTechnology}")
    fun getTopicsByTechnology(
            @Path("idTechnology")
            idTechnology:Int
    ):Observable<Response>


    @GET("user")
    fun getUserByNickPassword(
            @Query("nick") name: String,
            @Query("password") password: String
     ): Observable<Response>


    @FormUrlEncoded
    @POST("user")
    fun registerUser(
            @Field("nick") name: String,
            @Field("password") password: String,
            @Field("correo") email: String,
            @Field("nickgit") nickGit: String
    ): Observable<Response>

    @FormUrlEncoded
    @POST("technology")
    fun addTechnology(
            @Field("name") name: String
    ): Observable<Response>

    @FormUrlEncoded
    @POST("post")
    fun addPost(
            @Field ("idtopic") idTopic: Int,
            @Field ("iduser") idUser: Int,
            @Field ("content") content:String

    ): Observable<Response>

    @FormUrlEncoded
    @POST("category")
    fun addCategory(
            @Field("name") name: String
    ): Observable<Response>

    @FormUrlEncoded
    @POST("topic")
    fun addTopic(
            @Field("iduser") idUser: Int,
            @Field("idtechnology") idTechnology: Int,
            @Field("idcategory") idCategory: Int,
            @Field("name") name: String
    ):Observable<Response>


    @GET("repos/{username}")
    fun getReposByUser(
            @Path("username") name: String
    ): Observable<Response>


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