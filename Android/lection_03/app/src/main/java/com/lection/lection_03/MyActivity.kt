package com.lection.lection_03

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    private val adapter = MyAdapter()
    private var itemList = mutableListOf<Int>()
    private var scrollPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)
        recyclerView.adapter = adapter
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3
        recyclerView.layoutManager = GridLayoutManager(this, spanCount)

        fab.setOnClickListener {
            itemList.add(adapter.itemCount + 1)
            adapter.setItems(itemList)
            recyclerView.scrollToPosition(adapter.itemCount - 1)
        }

        if (savedInstanceState != null) {
            itemList = savedInstanceState.getIntegerArrayList("item_list")?.toMutableList()
                ?: mutableListOf()
            scrollPosition = savedInstanceState.getInt("scroll_position", 0)
            adapter.setItems(itemList)
            recyclerView.scrollToPosition(scrollPosition)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("item_list", ArrayList(itemList))
        outState.putInt("scroll_position",
            (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition())
    }
}



