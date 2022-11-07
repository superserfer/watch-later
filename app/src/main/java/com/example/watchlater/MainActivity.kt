package com.example.watchlater

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        const val EXTRA_MovieReminderDataItem = "EXTRA_MovieReminderDataItem"

        //        receive the reminder object after the user clicks on the notification
        fun newIntent(context: Context, movieReminder: MovieReminder): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_MovieReminderDataItem, movieReminder)
            return intent
        }


    }
}