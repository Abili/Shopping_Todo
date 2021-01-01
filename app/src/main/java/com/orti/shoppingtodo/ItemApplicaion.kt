package com.orti.shoppingtodo

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ItemApplicaion : Application() {
    private val coroutineScope = CoroutineScope(SupervisorJob())
    private val database by lazy {


        ItemRoomDatabase.getDatabase(this, coroutineScope)

    }
    val repository by lazy {
        ItemRepository(database.itemDao())
    }
}