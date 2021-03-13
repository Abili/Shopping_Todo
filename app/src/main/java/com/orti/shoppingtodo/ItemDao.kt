package com.orti.shoppingtodo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("select * from item_table WHERE itemName =:name")
    fun getItemsByName(name: String): LiveData<Item>

    @Query("select * from item_table")
    fun getItems(): LiveData<List<Item>>

    @Query("select * from item_table")
    fun getPrice(): LiveData<Item>

    @Insert
    fun insertItem(vararg item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrice(price: Item)

}

