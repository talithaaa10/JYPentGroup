package com.example.jypentgroup

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvGroups: RecyclerView
    private val list = ArrayList<Group>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        rvGroups = findViewById(R.id.rv_groups)
        rvGroups.setHasFixedSize(true)

        list.clear()
        list.addAll(getListGroups())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about_page) {
            val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(moveIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListGroups(): ArrayList<Group> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listGroup = ArrayList<Group>()
        for (i in dataName.indices) {
            val group = Group(
                name = dataName[i],
                description = dataDescription[i],
                photo = dataPhoto.getResourceId(i, -1)
            )
            listGroup.add(group)
        }
        dataPhoto.recycle()
        return listGroup
    }

    private fun showRecyclerList() {
        rvGroups.layoutManager = LinearLayoutManager(this)
        val listGroupAdapter = ListGroupAdapter(list)
        rvGroups.adapter = listGroupAdapter

        // Menangani klik pada setiap item di RecyclerView
        listGroupAdapter.setOnItemClickCallback(object : ListGroupAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Group) {
                showSelectedGroup(data)
            }
        })
    }

    private fun showSelectedGroup(group: Group) {
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_GROUP, group)
        startActivity(moveWithObjectIntent)
    }
}
