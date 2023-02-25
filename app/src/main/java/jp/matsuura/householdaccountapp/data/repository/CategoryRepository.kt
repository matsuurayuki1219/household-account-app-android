package jp.matsuura.householdaccountapp.data.repository

import jp.matsuura.householdaccountapp.data.converter.toEntity
import jp.matsuura.householdaccountapp.data.converter.toModel
import jp.matsuura.householdaccountapp.data.database.AppDatabase
import jp.matsuura.householdaccountapp.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val db: AppDatabase,
) {

    suspend fun getAll(): List<CategoryModel> {
        return withContext(Dispatchers.IO) {
            db.categoryDao().getAll().map { it.toModel() }
        }
    }

    suspend fun getById(categoryId: Int): CategoryModel {
        return withContext(Dispatchers.IO) {
            db.categoryDao().getById(categoryId = categoryId).toModel()
        }
    }

    suspend fun getFirstOfAll(): CategoryModel {
        return withContext(Dispatchers.IO) {
            db.categoryDao().getAll().first().toModel()
        }
    }

    suspend fun insert(category: CategoryModel) {
        return withContext(Dispatchers.IO) {
            val entity = category.toEntity()
            db.categoryDao().insert(entity = entity)
        }
    }

    suspend fun delete(category: CategoryModel) {
        return withContext(Dispatchers.IO) {
            val entity = category.toEntity()
            db.categoryDao().delete(entity = entity)
        }
    }

}