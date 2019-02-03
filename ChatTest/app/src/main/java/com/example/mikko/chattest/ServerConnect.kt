/*
Mikko Romo
1606176

Connects to the server and reads incoming messages from it.
*/
package com.example.mikko.chattest

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.Socket
import java.util.*

const val TAG1 = "ServerConnect"

class ServerConnect(activity: MainActivity, hostIP: String): Socket(), Runnable {

    private val mainActivity = activity
    private val ip = hostIP

    override fun run() {

        try {
            val s = Socket(ip, 30001)
            val output = PrintStream(s.getOutputStream(), true)
            val scanner = Scanner(s.getInputStream())
            mainActivity.runOnUiThread(Runnable {
                mainActivity.ps = output
                mainActivity.setUser()
            })

            while (true){
                val message = scanner.nextLine()
                if (message != null) {
                    val msg = message.split(" ", limit = 3)
                    mainActivity.runOnUiThread(Runnable {
                        mainActivity.updateMessage(ChatMessage(msg, mainActivity))
                    })
                }
            }


        } catch (e: Exception) {
            Log.d(TAG1, e.toString())
        } finally {
            Log.d(TAG1, "connection shut down")
        }

    }
}