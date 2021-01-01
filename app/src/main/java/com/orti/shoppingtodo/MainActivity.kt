package com.orti.shoppingtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.orti.shoppingtodo.ItemViewModel.*

class MainActivity() : AppCompatActivity() {

    private lateinit var addItemFab: FloatingActionButton
    private val itemViewModel: ItemViewModel by viewModels {
        ItemViewModelFactory((application as ItemApplicaion).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setFinishOnTouchOutside(false);
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val adapter = ItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel.allItem.observe(this, Observer { words ->
            words?.let { adapter.submitList(it) }
        })



        addItemFab = findViewById(R.id.fab)!!
        addItemFab.setOnClickListener {
            openActivityDialog()
        }
    }

    private fun openActivityDialog() {
        val fragmentManager = supportFragmentManager
        val newItemDialog = NewItemDialog()
        newItemDialog.show(fragmentManager, "items")
    }
}