package com.orti.shoppingtodo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "itemName")
    val name: String?,
    @ColumnInfo(name = "itemPrice")
    val price: String?
)