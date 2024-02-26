package com.josemaxp.birdpedy.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.josemaxp.birdpedy.dao.birdWatching.BirdWatch
import com.josemaxp.birdpedy.dao.birdWatching.BirdWatchDao
import com.josemaxp.birdpedy.dao.birds.Bird
import com.josemaxp.birdpedy.dao.birds.BirdDao
import com.josemaxp.birdpedy.dao.ccaa.Ccaa
import com.josemaxp.birdpedy.dao.ccaa.CcaaDao
import com.josemaxp.birdpedy.dao.families.Family
import com.josemaxp.birdpedy.dao.families.FamilyDao
import com.josemaxp.birdpedy.dao.order.Order
import com.josemaxp.birdpedy.dao.order.OrderDao
import com.josemaxp.birdpedy.dao.provinces.Province
import com.josemaxp.birdpedy.dao.provinces.ProvinceDao

@Database(entities = [Bird::class, BirdWatch::class, Ccaa::class, Order::class, Family::class, Province::class],  version = 1, exportSchema = false)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun birdDao() : BirdDao
    abstract fun birdWatchDao() : BirdWatchDao
    abstract fun ccaaDao() : CcaaDao
    abstract fun orderDao() : OrderDao
    abstract fun familyDao() : FamilyDao
    abstract fun provinceDao() : ProvinceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val databaseName = "BirdPedyDatabase"
            val tempInstance = INSTANCE
            if(tempInstance != null && (tempInstance.openHelper.databaseName ?: "") == databaseName){
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    databaseName)
                    .createFromAsset("database/birdpedy.db")
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }
}