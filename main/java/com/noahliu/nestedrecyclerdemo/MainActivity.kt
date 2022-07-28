package com.example.f

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private var myAdapter: MyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data: List<MyData> = makeData()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = MyAdapter(data, object : MyAdapter.OnItemClick {
            override fun onItemClick(data: MyData.NestedData?, myData: MyData?) {
                if (myData != null) {
                    if (data != null) {
                        Toast.makeText(
                            this@MainActivity,
                            "chosen" + myData.title + "of " + data.nesTitle,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
        recyclerView.adapter = myAdapter
    }

    private fun makeData(): List<MyData> {
        val data: MutableList<MyData> = ArrayList()
        val title = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
        val nesTitle = arrayOf(
            "Braised beef", "Tomato scrambled eggs",
            "Fried chicken drumsticks", "Sweet and sour fish", "Baked stewed rice",
            "Fried shrimps", "Braised salmon"
        )
        for (i in title.indices) {
            val r = (Math.random() * 7).toInt()
            val nesArray: MutableList<MyData.NestedData> = ArrayList()
            for (j in 0 until r + 1) {
                nesArray.add(MyData.NestedData(nesTitle[(Math.random() * 7).toInt()]))
            }
            data.add(MyData(title[i], nesArray))
        }
        return data
    }
}
