package com.ojitos369.firetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.messaging.FirebaseMessaging
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // load text view with id "elToken"
        val elToken = findViewById<android.widget.TextView>(R.id.elToken)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                // print error message in logs
                println("Error getting token: ${task.exception}")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            elToken.text = token

            // print token in logs
            // println("Token: $token")
            // Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
            // Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })

        Firebase.messaging.subscribeToTopic("push_not")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                // println(msg)
                elToken.text = msg
                // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
    }
}