package com.example.rvcamera.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rvcamera.R
import com.example.rvcamera.adapter.BaseAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: BaseAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = BaseAdapter()

    }
}