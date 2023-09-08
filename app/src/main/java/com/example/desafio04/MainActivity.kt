package com.example.desafio04

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val taskList = mutableListOf<String>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        binding.taskList.adapter = adapter

        binding.addButton.setOnClickListener {
            val task = binding.taskInput.text.toString()
            if (task.isNotBlank()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
                binding.taskInput.text.clear()
            }
        }
    }
}
