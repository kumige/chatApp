/*
Mikko Romo
1606176

Login screen that takes the username and ip.
Starts the chat activity.
*/
package com.example.mikko.chattest

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonConnect.setOnClickListener{
            when {
                username.text.isNullOrEmpty() -> Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
                ip.text.isNullOrEmpty() -> Toast.makeText(this, "Please enter a valid ip", Toast.LENGTH_SHORT).show()
                else -> connect()
            }
        }
    }

    fun connect(){
        val args = Bundle()
        val username = username.text.toString()
        val ip = ip.text.toString()
        args.putString("username", username)
        args.putString("ip", ip)
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtras(args)
        }
        startActivity(intent)
    }
}