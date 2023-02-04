package jp.matsuura.householdaccountapp.data.database.entiry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "category_name") val categoryName: String,
    @ColumnInfo(name = "category_type") val categoryType: Int,
)