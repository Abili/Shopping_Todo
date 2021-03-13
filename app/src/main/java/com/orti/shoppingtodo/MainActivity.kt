package com.orti.shoppingtodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.orti.shoppingtodo.addItems.NewItemDialog
import com.orti.shoppingtodo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), ItemAdapter.ItemAction {

    private lateinit var addItemFab: FloatingActionButton
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        this.setFinishOnTouchOutside(false)

        val adapter = ItemAdapter()

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        val factory = ItemViewModelFactory.createFactory(this)
        itemViewModel = ViewModelProvider(this, factory).get(ItemViewModel::class.java)
        itemViewModel.getItem().observe(this, { items ->
            items?.let { adapter.submitList(items) }
        })

        binding.fab.setOnClickListener {
            openActivityDialog()
        }
    }


    private fun openActivityDialog() {
        val fragmentManager = supportFragmentManager
        val newItemDialog = NewItemDialog(itemViewModel)
        newItemDialog.show(fragmentManager, "items")
    }

    override fun onClick(getAdapterPosition: FieldPosition) {
        TODO("Not yet implemented")
    }
}