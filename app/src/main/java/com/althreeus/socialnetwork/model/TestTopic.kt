package com.althreeus.socialnetwork.model

/**
 * Created by jr on 05-Mar-18.
 */

class TestTopic(

        var id: Int=0,
        var idUser:Int=0,
        var nick:String="",
        var idTechnology:Int=0,
        var nameTechnology: String="",
        var idCategory:Int=0,
        var nameCategory: String="",
        var content:String="",
        var date:String="" ) {

    companion object {

        val TOPICS: ArrayList<TestTopic> = ArrayList()

        init {
            TOPICS.add(TestTopic(0, 0, "jesusmad", 0, "Kotlin", 0, "Repo",
                                "PokemonKotlin", "05/03/2018"))
            TOPICS.add(TestTopic(1, 1, "alberto", 1, "Swift", 0, "Repo",
                    "DadosSwift", "01/02/2018"))
            TOPICS.add(TestTopic(2, 2, "alejandro", 2, "Java", 0, "Repo",
                    "ServidorUDPJava", "22/01/2018"))
            TOPICS.add(TestTopic(3, 3, "alvaro", 3, "PHP", 0, "Repo",
                    "PaginaWEMOS", "11/02/2018"))

        }
    }
}