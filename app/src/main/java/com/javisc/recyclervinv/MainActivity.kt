package com.javisc.recyclervinv

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = generateItems()

        recyclerViewItem.layoutManager = LinearLayoutManager(this)
        val itemAdapter = ItemAdapter(object : ItemAdapter.OnClickListener {
            override fun onClick(item: Item) {
                val text = "Day: ${item.day} Number of Tasks: ${item.subItemList.size}"
                Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
            }
        })
        itemAdapter.submitList(itemList)
        recyclerViewItem.adapter = itemAdapter
    }

    private fun generateItems(): List<Item> {
        val itemList = mutableListOf<Item>()

        for (i in 1..200) {

            val subItemList = mutableListOf<SubItem>()

            val randomNumberOfTask = Random.nextInt(4, 12)

            for (j in 1..randomNumberOfTask) {
                val randomNumber = Random.nextInt(1, 8)
                val randomNumber2 = Random.nextInt(8, 16)
                subItemList.add(SubItem(randomNumber, randomNumber2))
            }

            itemList.add(Item(i, subItemList))
        }
        return itemList
    }
}
