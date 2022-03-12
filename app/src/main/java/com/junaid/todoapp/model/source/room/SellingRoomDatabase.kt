package com.junaid.todoapp.model.source.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.junaid.todoapp.model.data.responseModels.SellItem
import com.junaid.todoapp.utils.generals.AppConstants.DATABASE_NAME

@Database(entities = [SellItem::class], version = 1, exportSchema = false)
abstract class SellingRoomDatabase : RoomDatabase() {
    abstract fun sellItemsDao(): SellItemsDao

    companion object {
        @Volatile
        private var INSTANCE: SellingRoomDatabase? = null
        fun getDatabase(context: Context): SellingRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SellingRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}