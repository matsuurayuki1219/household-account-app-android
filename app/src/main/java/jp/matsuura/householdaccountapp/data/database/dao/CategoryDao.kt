package jp.matsuura.householdaccountapp.data.database.dao

import androidx.room.*
import jp.matsuura.householdaccountapp.data.database.entiry.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getById(categoryId: Int): CategoryEntity

    @Insert
    fun insert(entity: CategoryEntity)

    @Delete
    fun delete(entity: CategoryEntity)

}