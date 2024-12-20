package com.duma.bungapedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // data dari intent
        val flower: Flower? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(MainActivity.DATA, Flower::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(MainActivity.DATA)
        }

        //Menyertakan elemen layout
        val imgDetail: ImageView = findViewById(R.id.tv_detail_image)
        val nameDetail: TextView = findViewById(R.id.tv_detail_name)
        val descriptionDetail: TextView = findViewById(R.id.tv_detail_description)

        flower?.let {
            nameDetail.text = it.name
            descriptionDetail.text = it.description
            imgDetail.setImageResource(it.photo)
        }
        // Menambahkan tombol "back" di Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    // Handle tombol back pada Toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // menu share
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareBotton -> {
                shareApp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareApp() {
        val appMsg: String = "Hey, ayo kunjungi aplikasi Bungapedia untuk mendapatkan rekomendasi bunga indah untuk menghiasi hidupmu! ikuti tautan berikut dan download aplikasi Bungapedia: " +
                "https://play.google.com/store/apps/details?id=com.duma.bungapedia 🌺🌻"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, appMsg)
        intent.type = "text/plain"
        startActivity(intent)
    }
}