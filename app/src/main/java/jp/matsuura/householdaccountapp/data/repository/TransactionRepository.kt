package jp.matsuura.householdaccountapp.data.repository

import jp.matsuura.householdaccountapp.data.converter.toModel
import jp.matsuura.householdaccountapp.data.database.AppDatabase
import jp.matsuura.householdaccountapp.data.database.entiry.TransactionEntity
import jp.matsuura.householdaccountapp.model.CategoryModel
import jp.matsuura.householdaccountapp.model.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val db: AppDatabase,
) {
    suspend fun insert(categoryId: Int, moneyAmount: Int) {
        withContext(Dispatchers.IO) {
            val entity = TransactionEntity(
                id = 0,
                categoryId = categoryId,
                moneyAmount = moneyAmount,
                createdAt = Date(),
                updatedAt = Date(),
            )
            db.transactionDao().insert(entity = entity)
        }
    }
}