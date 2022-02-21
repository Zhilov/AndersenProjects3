package com.example.loadimage

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var editSearch: EditText
    private lateinit var imageView: ImageView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editSearch = findViewById(R.id.edit_search)
        imageView = findViewById(R.id.image_view)
        button = findViewById(R.id.button_search)

        button.setOnClickListener {
            if (editSearch.text.isNotEmpty()) loadPicture(editSearch.text.toString())
        }

    }

    private fun loadPicture(string: String) {
        val executor = Executors.newSingleThreadExecutor()!!
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            try {
                val stream = java.net.URL(string).openStream()
                val image = BitmapFactory.decodeStream(stream)
                handler.post {
                    imageView.setImageBitmap(image)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}