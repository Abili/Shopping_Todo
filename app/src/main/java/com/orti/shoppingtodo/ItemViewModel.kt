package com.orti.shoppingtodo

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    val allItem: LiveData<List<Item>> = repository.allItem.asLiveData()
    fun insert(item: ArrayList<Int>, price: Int) = viewModelScope.launch {
        repository.insertItem(item)
        repository.insertPrice(price)
    }

    class ItemViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ItemViewModel(repository) as T
            }
            throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
        }
    }
}