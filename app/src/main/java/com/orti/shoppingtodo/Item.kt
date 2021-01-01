package com.orti.shoppingtodo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
class Item (
    @PrimaryKey (autoGenerate = true) val id:Int,
    @ColumnInfo(name = "item") val itemName :String,
    @ColumnInfo(name = "price") val itemPrice :Int
)