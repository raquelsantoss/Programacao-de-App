package com.example.desafio04

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val taskList = mutableListOf<String>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        binding.taskList.adapter = adapter


        listView = binding.taskList

        binding.addButton.setOnClickListener {
            val task = binding.taskInput.text.toString()
            if (task.isNotBlank()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
                binding.taskInput.text.clear()
            }
        }

        // Defina o ouvinte para toques longos no ListView
        listView.setOnItemLongClickListener { _, _, position, _ ->
            val task = taskList[position]
            showDeleteDialog(task, position)
            true
        }
    }

    private fun showDeleteDialog(task: String, position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Excluir Tarefa")
            .setMessage("Deseja realmente excluir a tarefa: \"$task\"?")
            .setPositiveButton("Sim") { _, _ ->

                taskList.removeAt(position)
                (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
