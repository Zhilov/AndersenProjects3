package com.example.loadimage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var editSearch: EditText
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editSearch = findViewById(R.id.edit_search)
        imageView = findViewById(R.id.image_view)

        editSearch.addTextChangedListener(object : TextWatcher {

            private var timer = Timer()

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        loadPicture(p0)
                    }
                }, 1500)
            }

        })
    }

    private fun loadPicture(editable: Editable?) {
        val executor = Executors.newSingleThreadExecutor()!!
        val handler = Handler(Looper.getMainLooper())
        lateinit var image: Bitmap

        executor.execute {
            try {
                val stream = java.net.URL(editable.toString()).openStream()
                image = BitmapFactory.decodeStream(stream)
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