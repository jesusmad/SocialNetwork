package com.althreeus.socialnetwork.services

import android.util.Log
import com.althreeus.socialnetwork.model.GithubUser
import com.althreeus.socialnetwork.model.Repository
import rx.schedulers.Schedulers

/**
 * Created by alberto on 21/03/2018.
 */
class GitHubService : ServiceBase() {

    companion object {
        val instance = GitHubService()
    }

    fun getGithubUser(nick: String): GithubUser?{

        var user: GithubUser? = null

        githubService.getUser(nick)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->
                            user = body
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        },
                        { error ->
                            Log.e("GITHUB", error.message)
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

    fun getRepositories(nick: String): List<Repository>{

        var repositories = mutableListOf<Repository>()

        githubService.getRepositories(nick)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->
                            repositories = body.toMutableList()
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        },
                        { error ->
                            Log.e("GITHUB", error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }
                )
        synchronized(monitor){
            monitor.wait()
        }

        return repositories.toList()
    }
}