/*
Mikko Romo
1606176

Formats messages coming from the server into separate strings for ListAdapter.
User lists and message histories create a new dialog.
*/
package com.example.mikko.chattest

class ChatMessage(list: List<String>, mainActivity: MainActivity){
    var username: String
    var message: String
    var timestamp: String

    init {
        when {
            list[0].startsWith("2") -> {
                val ts = list[0].slice(11..15)
                val user = list[1].dropLast(1)
                message = list[2]
                username = user
                timestamp = ts

            }
            list.size < 2 -> {
                message = list[0]
                username = ""
                timestamp = ""
            }
            list.size < 3 -> {
                message = list[0] + " " + list[1]
                username = ""
                timestamp = ""
            }
            else -> {
                message = "${list[0]} ${list[1]} ${list[2]}"
                username = ""
                timestamp = ""
            }
        }

        if (message.startsWith("/u")){
            val msg = message.removePrefix("/u")
            val list = msg.replace(" ", "\n")
            mainActivity.popUpWindow(list)
        }
        if (message.startsWith("/h")){
            val msg = message.removePrefix("/h")
            val list = msg.replace("|", "\n")
            mainActivity.popUpWindow(list)
        }
    }

}