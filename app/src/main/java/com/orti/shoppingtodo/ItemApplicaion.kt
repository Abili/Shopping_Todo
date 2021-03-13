package com.orti.shoppingtodo

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ItemApplicaion : Application() {
//    private val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(
//        ItemRoomDatabase.NUMBER_OF_THREADS
//    )
//    private val database by lazy {
//        ItemRoomDatabase.getInstance(this)
//
//    }
//    val repository by lazy {
//        ItemRepository( database.itemDao(),databaseWriteExecutor);
//    }
}