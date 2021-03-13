package com.orti.shoppingtodo

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Handles data sources and execute on the correct thread.
 */
class ItemRepository(dao: ItemDao, private val mIoExecutor: ExecutorService) {
    private val mDao: ItemDao = dao
    //private val getTea: LiveData<Item>
    //private val item: Item


    fun insert(item: Item) {
        mIoExecutor.execute { mDao.insertItem(item) }
    }

//    fun delete(tea: Item?) {
//        mIoExecutor.execute { mDao.delete(tea) }
//    }

    fun getItem(): LiveData<List<Item>> {
        return mDao.getItems()
    }


    companion object {
        @Volatile
        private var sInstance: ItemRepository? = null
        private const val PAGE_SIZE = 20
        fun getInstance(
            context: Context?
        ): ItemRepository? {
            if (sInstance == null) {
                synchronized(ItemRepository::class.java) {
                    if (sInstance == null) {
                        val database: ItemRoomDatabase =
                            ItemRoomDatabase.getDatabase(context!!)
                        sInstance = ItemRepository(
                            database.itemDao(),
                            Executors.newSingleThreadExecutor()
                        )
                    }
                }
            }
            return sInstance
        }
    }

    init {
        //val item = Item(null, null, null)
        //getTea = mDao.getItems(item.itemName!!)
    }
}