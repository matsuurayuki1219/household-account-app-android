package jp.matsuura.householdaccountapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jp.matsuura.householdaccountapp.data.database.dao.CategoryDao
import jp.matsuura.householdaccountapp.data.database.dao.TransactionDao
import jp.matsuura.householdaccountapp.data.database.entiry.CategoryEntity
import jp.matsuura.householdaccountapp.data.database.entiry.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        TransactionEntity::class,
    ],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}