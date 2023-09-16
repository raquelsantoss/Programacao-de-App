package com.example.desafio05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var lifecycleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encontre a TextView pelo ID
        lifecycleTextView = findViewById(R.id.lifecycleTextView)

        updateLifecycleText("onCreate() foi chamado")
    }

    override fun onStart() {
        super.onStart()
        updateLifecycleText("onStart() foi chamado")
    }

    override fun onResume() {
        super.onResume()
        updateLifecycleText("onResume() foi chamado")
    }

    override fun onPause() {
        super.onPause()
        updateLifecycleText("onPause() foi chamado")
    }

    override fun onStop() {
        super.onStop()
        updateLifecycleText("onStop() foi chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        updateLifecycleText("onDestroy() foi chamado")
    }

    private fun updateLifecycleText(message: String) {
        val currentText = lifecycleTextView.text.toString()
        val newText = "$currentText\n$message"
        lifecycleTextView.text = newText
    }
}