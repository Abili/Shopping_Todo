package com.orti.shoppingtodo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    private class ItemDataBaseCallBack(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val itemDao = database.itemDao()
                    val item = Item(1, "water melon", 2400)
                    itemDao.insertItem(item)

                    val item2 = Item(1, "water jerricans", 3700)
                    itemDao.insertItem(item2)

                }
            }
        }
    }


    companion object {
        private var INSTANCE: ItemRoomDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): ItemRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"

                ).addCallback(ItemDataBaseCallBack(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}