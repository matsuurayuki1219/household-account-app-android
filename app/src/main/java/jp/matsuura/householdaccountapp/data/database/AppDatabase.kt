package jp.matsuura.householdaccountapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.matsuura.householdaccountapp.data.database.dao.CategoryDao
import jp.matsuura.householdaccountapp.data.database.entiry.CategoryEntity

@Database(
    entities = [
        CategoryEntity::class,
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}