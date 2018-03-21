package com.althreeus.socialnetwork.services

import android.util.Log
import com.althreeus.socialnetwork.model.Post
import com.althreeus.socialnetwork.model.Technology
import com.althreeus.socialnetwork.model.Topic
import com.althreeus.socialnetwork.model.User
import com.althreeus.socialnetwork.views.RegisterActivity
import rx.schedulers.Schedulers

/**
 * Created by Alejandro on 20/03/2018.
 */
class SocialNetworkService : ServiceBase() {

    companion object {
        val instance = SocialNetworkService()
        var userLogged: User? = null
    }

    fun getUser(iduser: Int): User? {

        var user:User? = null


        apiService.getUser(iduser)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->

                            user = body.user


                            synchronized(monitor){
                                monitor.notifyAll()
                            }



                        },
                        { error ->
                            Log.d("Gestor",error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }


                )

        synchronized(monitor){
            monitor.wait()
        }



        return user


    }


    fun getTopics():List<Topic>{

        var topics = ArrayList<Topic>()
        apiService.getTopics()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->

                            topics = body.topics


                            synchronized(monitor){
                                monitor.notifyAll()
                            }



                        },
                        { error ->
                            Log.d("Gestor",error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }


                )

        synchronized(monitor){
            monitor.wait()
        }



        return topics
    }

    fun getTopic(idTopic:Int):Topic?{

        var topic:Topic? = null
        apiService.getTopic(idTopic)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->

                            topic = body.topic


                            synchronized(monitor){
                                monitor.notifyAll()
                            }



                        },
                        { error ->
                            Log.d("Gestor",error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }


                )

        synchronized(monitor){
            monitor.wait()
        }



        return topic


    }

    fun getPost(idTopic: Int): ArrayList<Post> {

        var posts = ArrayList<Post>()
        apiService.getPosts(idTopic)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->

                            posts = body.posts


                            synchronized(monitor){
                                monitor.notifyAll()
                            }



                        },
                        { error ->
                            Log.d("Gestor",error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }


                )

        synchronized(monitor){
            monitor.wait()
        }



        return posts


    }

    fun getPostsByTechnology(idTechnology: Int):ArrayList<Topic>{

        var topics = ArrayList<Topic>()
        apiService.getTopicsByTechnology(idTechnology)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->

                            topics = body.topics


                            synchronized(monitor){
                                monitor.notifyAll()
                            }



                        },
                        { error ->
                            Log.d("Gestor",error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }


                )

        synchronized(monitor){
            monitor.wait()
        }



        return topics



    }


    fun getUserByNickPassword(name: String, password: String): User?{

        var user: User? = null

        apiService.getUserByNickPassword(name, password)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->
                            user = body.user

                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        },
                        { error ->
                            Log.e("Gestor", error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }
                )

        synchronized(monitor){
            monitor.wait()
        }

        return user
    }

    fun registerUser(name: String, password: String, email: String, nickGit: String): User?{

        var user: User? = null

        apiService.registerUser(name, password, email, nickGit)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->
                            user = body.user

                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        },
                        { error ->
                            Log.e("Gestor", error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }
                )

        synchronized(monitor){
            monitor.wait()
        }

        return user
    }

}