package com.orti.shoppingtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class ItemViewModel(private var repository: ItemRepository) : ViewModel() {

    //private val mTea: LiveData<Item> = mRepository.getItem(item.itemName)

    fun getItem(): LiveData<List<Item>> {
        return repository.getItem()
    }

    fun insertItem(item: Item) {
        repository.insert(item)
    }


}
