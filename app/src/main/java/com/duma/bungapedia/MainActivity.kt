package com.duma.bungapedia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFlowers: RecyclerView
    private val list = ArrayList<Flower>()

    companion object{
        const val DATA = "data_flower"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFlowers = findViewById(R.id.rv_flowers)
        rvFlowers.setHasFixedSize(true)

        list.addAll(getListFlowers())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvFlowers.layoutManager = LinearLayoutManager(this)
        val listFlowerAdapter = ListFlowerAdapter(list)
        rvFlowers.adapter = listFlowerAdapter

        listFlowerAdapter.setOnItemClickCallback(object : ListFlowerAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Flower) {
                showSelectedFlower(data)
            }
        })
    }

    private fun showSelectedFlower(flower: Flower){
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DATA, flower)
        startActivity(moveWithObjectIntent)
    }

    private fun getListFlowers(): ArrayList<Flower> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listFlower = ArrayList<Flower>()
        for (i in dataName.indices) {
            val flower = Flower(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1)
            )
            listFlower.add(flower)
        }
        dataPhoto.recycle()
        return listFlower
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvFlowers.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvFlowers.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_profile -> {
                val profileIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(profileIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}