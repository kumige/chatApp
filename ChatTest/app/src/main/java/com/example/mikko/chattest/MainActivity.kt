/*
Mikko Romo
1606176

Chat activity.
*/
package com.example.mikko.chattest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.mikko.chattest.R.menu.app_bar
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintStream

class MainActivity : AppCompatActivity() {

    lateinit var messageListAdapter: ListAdapter
    lateinit var ps: PrintStream
    var username = ""
    var ip = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        messageListAdapter = ListAdapter(this)
        messageListView.adapter = messageListAdapter

        username = intent.getStringExtra("username")
        ip = intent.getStringExtra("ip")

        val t = Thread(ServerConnect(this, ip))
        t.start()

        sendFAB.setOnClickListener{
            if (!inputField.text.toString().isEmpty()) {
                val send = Thread(SendMessage(inputField.text.toString(), ps))
                send.start()
            }
            inputField.text?.clear()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        Thread(SendMessage(":quit", ps)).start()
    }

    fun setUser(){
        Thread(SendMessage(":user $username", ps)).start()
    }

    fun updateMessage(s: ChatMessage){
        if(!s.message.startsWith("/u")) {
            if(!s.message.startsWith("/h")) {
                messageListAdapter.add(s)
                messageListView.smoothScrollByOffset(messageListAdapter.count - 1)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_userList -> {
            Thread(SendMessage(":listUsers", ps)).start()
            true
        }
        R.id.action_history -> {
            Thread(SendMessage(":history", ps)).start()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
    fun popUpWindow(str: String){
        Thread {
            val dialog = PopUpDialog()
            val args = Bundle()
            args.putString("content", str)
            dialog.arguments = args
            dialog.show(supportFragmentManager, TAG1)
        }.start()
    }

}
