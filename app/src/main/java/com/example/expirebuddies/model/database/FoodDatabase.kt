package com.example.expirebuddies.model.database

import android.content.Context
import androidx.core.app.NavUtils
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDatabase:RoomDatabase(
) {
    abstract val foodDao:FoodDao
    companion object{
        const val DATABASE_NAME="foods_db"
//        @Volatile
//        private var INSTANCE:FoodDatabase?=null
//        private fun buildDatabase(context: Context):FoodDatabase{
//            return Room.databaseBuilder(context,FoodDatabase::class.java,"foods.db").build()
//        }
//        fun getDatabaseInstance(context: Context):FoodDatabase{
//            if (INSTANCE==null){
//                    synchronized(this){
//                        INSTANCE= buildDatabase(context)
//                }
//            }
//            return INSTANCE!!
//        }
   }

}