package com.example.booklibrarysqlite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val createView: AppCompatButton = findViewById(R.id.createView_button)
        createView.setOnClickListener {
            startActivity(Intent(applicationContext, CreateBook::class.java))
        }

        val viewButton: AppCompatButton = findViewById(R.id.view_button)
        viewButton.setOnClickListener {
            startActivity(Intent(this, ViewBook::class.java))
        }
    }
}