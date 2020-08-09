/*
 * Copyright (c) 2020, Cartrack Challenge. All rights reserved.
 *
 * Created by Harold Calayan on 8/10/2020
 *
 */

package com.haroldcalayan.cartrackchallenge.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.haroldcalayan.cartrackchallenge.data.model.Account
import com.haroldcalayan.cartrackchallenge.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Account::class, User::class], version = 1, exportSchema = false)
abstract class CartrackRoomDatabase : RoomDatabase() {

    abstract fun accountDao() : AccountDao
    abstract fun userDao() : UserDao

    private class IdeaDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.accountDao(), database.userDao())
                }
            }
        }

        suspend fun populateDatabase(accountDao: AccountDao, userDao: UserDao) {
            accountDao.deleteAll()
            userDao.deleteAll()
            // Insert data here
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: CartrackRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CartrackRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartrackRoomDatabase::class.java,
                    "cartrack_database"
                )
                    .addCallback(IdeaDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}