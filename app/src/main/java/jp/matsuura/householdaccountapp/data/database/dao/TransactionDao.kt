package jp.matsuura.householdaccountapp.data.database.dao

import androidx.room.*
import jp.matsuura.householdaccountapp.data.database.entiry.TransactionEntity

@Dao
interface TransactionDao {

    @Insert
    fun insert(entity: TransactionEntity)

}