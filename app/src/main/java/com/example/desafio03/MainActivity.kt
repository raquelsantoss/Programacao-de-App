package com.example.desafio03

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var colorListView: ListView
    private lateinit var colorAdapter: ArrayAdapter<String>
    private val colorList = listOf(
        ColorItem("Vermelho", R.color.colorRed),
        ColorItem("Verde", R.color.colorGreen),
        ColorItem("Azul", R.color.colorBlue),
        ColorItem("Amarelo", R.color.colorYellow),
        ColorItem("Roxo", R.color.colorPurple),
        ColorItem("Laranja", R.color.colorOrange)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorListView = findViewById(R.id.colorListView)
        colorAdapter = ArrayAdapter(this, R.layout.list_item_color, colorList.map { it.name })
        colorListView.adapter = colorAdapter

        colorListView.setOnItemClickListener { _, _, position, _ ->
            val selectedColor = colorList[position]
            showToast(selectedColor)
        }
    }

    private fun showToast(selectedColor: ColorItem) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.toast_custom, findViewById(R.id.toast_layout_root))

        val toastText = layout.findViewById<View>(R.id.toastText) as TextView
        toastText.text = selectedColor.name

        val textColorResId = selectedColor.colorResId
        toastText.setTextColor(resources.getColor(textColorResId))

        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }
}
