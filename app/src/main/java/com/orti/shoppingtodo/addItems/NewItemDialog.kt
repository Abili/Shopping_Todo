package com.orti.shoppingtodo.addItems

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.orti.shoppingtodo.Item
import com.orti.shoppingtodo.ItemViewModel
import com.orti.shoppingtodo.R
import com.orti.shoppingtodo.databinding.DialogNewItemBinding

class NewItemDialog(private val viewModel: ItemViewModel) : DialogFragment() {

    private var item: Item? = null
    lateinit var binding: DialogNewItemBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            binding = DialogNewItemBinding.inflate(LayoutInflater.from(context))

            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                // Add action buttons
                .setPositiveButton(
                    R.string.ok
                ) { dialog, id ->
                    // sign in the user ...
                    val name = binding.itemname.text.toString()
                    val price = binding.price.text.toString()
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price)) {
                        val error = "required"
                        binding.itemname.error = error
                        binding.price.error = error
                    } else {
                        item = Item(null, name, price)
                        viewModel.insertItem(item!!)
                        dialog.cancel()
                    }


                }
                .setNegativeButton(
                    R.string.cancel
                ) { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}



