package com.tar.investnotes.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tar.investnotes.database.models.*
import io.reactivex.Observable
import java.util.ArrayList

@Database(
    entities = [
        UserR::class,
        OwnerR::class,
        BrokerR::class,
        InvestTypeR::class,
        InvestmentR::class
    ], version = 1, exportSchema = false
)

abstract class StoreDatabase : RoomDatabase() {

    abstract val storeDao: StoreDao

    companion object {

        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getInstance(context: Context): StoreDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StoreDatabase::class.java,
                        "investnotes_database"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    open fun getUsers() : Observable<List<UserR>> {
        return Observable.fromCallable { storeDao.getUsers() ?: ArrayList() }
    }


}