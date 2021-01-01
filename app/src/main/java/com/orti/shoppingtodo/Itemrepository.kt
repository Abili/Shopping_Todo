package com.orti.shoppingtodo

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {
    val allItem:Flow<List<Item>> = itemDao.getItems()
    var allPrices: List<Item> = itemDao.getPrice()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertItem(item: ArrayList<Int>){
        itemDao.insertItem(item)

    }
    suspend fun insertPrice(price: Int){
        itemDao.insertPrice(price)
    }


}