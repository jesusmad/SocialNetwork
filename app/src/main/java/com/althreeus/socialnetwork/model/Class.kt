package com.althreeus.socialnetwork.model

import java.io.Serializable


/**
 * Created by Alejandro on 28/02/2018.
 */
data class User (val id: Int,val nick: String,val password:String,val email:String,val idgit:Int,val nickgit:String, var avatar_url: String=""): Serializable
data class Post(val id:Int,val idtopic: Int,val iduser: Int,val nick: String,val content:String,val date:String): Serializable
data class Category(val id:Int,val name:String)
data class Technology(val id:Int,val name:String,val logo:String,val color: String)
data class Topic(val id: Int,val iduser:Int,val nick:String,val name:String,val description:String,val url:String,val idtechnology:Int,val nametechnology: String,val idcategory:Int,val namecategory: String,val date:String): Serializable
data class Response(val users:ArrayList<User>, val categorys:ArrayList<Category>, val posts:ArrayList<Post>, val technologys:ArrayList<Technology>, val topics:ArrayList<Topic>, val repos:ArrayList<Topic>, val user: User, val topic: Topic, val post: Post, val technology: Technology, val category: Category)


//Object for testing

data class TestTopic(var id: Int=0,var idUser:Int=0,var nick:String="",var idTechnology:Int=0,var nameTechnology: String="",var idCategory:Int=0,var date:String="",var nameCategory: String="")
data class TestCategory(var id:Int=0,var name:String="")
data class TestTechnology(var id:Int=0,var name:String="",var logo:String="",var color: String="")
data class TestUser(var id: Int=0,var nick: String="",var password:String="",var email:String="",var idGit:Int=0,var nickGit:String="")
data class TestPost(var id:Int=0,var idTopic: Int=0,var idUser: Int=0,var content:String="",var date:String="")



//Object for GitHub data
data class GithubUser(val id: Int, val login: String, val avatar_url: String): Serializable
data class Repository (var id: Int=0, var name: String="")
