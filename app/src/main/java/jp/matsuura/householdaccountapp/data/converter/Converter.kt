package jp.matsuura.householdaccountapp.data.converter

import jp.matsuura.householdaccountapp.data.database.entiry.CategoryEntity
import jp.matsuura.householdaccountapp.model.CategoryModel
import jp.matsuura.householdaccountapp.model.CategoryType

fun CategoryEntity.toModel(): CategoryModel {
    return CategoryModel(
        id = id,
        categoryName = categoryName,
        categoryType = when (categoryType) {
            0 -> CategoryType.SPENDING
            1 -> CategoryType.INCOME
            else -> throw IllegalArgumentException("category type is invalid")
        }
    )
}

fun CategoryModel.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        categoryName = categoryName,
        categoryType = when (categoryType) {
            CategoryType.SPENDING -> 0
            CategoryType.INCOME -> 1
        }
    )
}