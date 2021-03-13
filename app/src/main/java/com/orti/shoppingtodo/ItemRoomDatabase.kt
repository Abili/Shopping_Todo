package com.orti.shoppingtodo

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Item::class], version = 1)
abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao


    companion object {
        private var mContext: Context? = null
        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                databaseWriteExecutor.execute { fillWithStartingData(mContext) }
            }
        }

        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(
            context: Context,
        ): ItemRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    .addCallback(sRoomDatabaseCallback)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


        @WorkerThread
        open fun fillWithStartingData(context: Context?) {
            val dao: ItemDao = getDatabase(context!!).itemDao()
            val items = loadJsonArray(context)
            try {
                for (i in 0 until items!!.length()) {
                    val item = items.getJSONObject(i)
                    dao.insertItem(
                        Item(
                            item.getInt("id"),
                            item.getString("name"),
                            item.getString("price")
                        )
                    )
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        private fun loadJsonArray(context: Context?): JSONArray? {
            val builder = StringBuilder()
            val `in` = context!!.resources.openRawResource(R.raw.sample_teas)
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                val json = JSONObject(builder.toString())
                return json.getJSONArray("items")
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
            return null
        }

    }
}