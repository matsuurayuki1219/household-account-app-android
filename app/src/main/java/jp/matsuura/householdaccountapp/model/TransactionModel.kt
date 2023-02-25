package jp.matsuura.householdaccountapp.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

data class TransactionModel(
    val categoryId: Int,
    val createdAt: Date,
    val moneyAmount: Int,
    val updatedAt: Date,
)