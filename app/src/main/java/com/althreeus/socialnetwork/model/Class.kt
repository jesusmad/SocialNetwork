package com.althreeus.socialnetwork.model


/**
 * Created by Alejandro on 28/02/2018.
 */
data class User (val id: Int,val nick: String,val password:String,val email:String,val idGit:Int,val nickGit:String)
data class Post(val id:Int,val idTopic: Int,val idUser: Int,val content:String,val date:String)
data class Category(val id:Int,val name:String)
data class Technology(val id:Int,val name:String,val logo:String,val color: String)
data class Topic(val id: Int,val idUser:Int,val nick:String,val idTechnology:Int,val nameTechnology: String,val idCategory:Int,val nameCategory: String,val date:String)
data class Response(val users:ArrayList<User>, val categories:ArrayList<Category>, val posts:ArrayList<Post>, val technologies:ArrayList<Technology>, val topics:ArrayList<Topic>, val user: User, val topic: Topic, val post: Post, val technology: Technology, val category: Category)



//Object for testing

data class TestTopic(var id: Int=0,var idUser:Int=0,var nick:String="",var idTechnology:Int=0,var nameTechnology: String="",var idCategory:Int=0,var date:String="",var nameCategory: String="")
data class TestCategory(var id:Int=0,var name:String="")
data class TestTechnology(var id:Int=0,var name:String="",var logo:String="",var color: String="")
data class TestUser(var id: Int=0,var nick: String="",var password:String="",var email:String="",var idGit:Int=0,var nickGit:String="")
data class TestPost(var id:Int=0,var idTopic: Int=0,var idUser: Int=0,var content:String="",var date:String="")



//Object for GitHub data

data class Repository (var id: Int=0, var name: String="")
