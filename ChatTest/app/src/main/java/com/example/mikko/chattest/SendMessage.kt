/*
Mikko Romo
1606176

Sends a message to the server.
*/
package com.example.mikko.chattest

import java.io.PrintStream

class SendMessage(message: String, ps: PrintStream): Runnable {
    private val chatMessage = message
    private val output = ps

    override fun run() {
        output.println(chatMessage)
    }
}