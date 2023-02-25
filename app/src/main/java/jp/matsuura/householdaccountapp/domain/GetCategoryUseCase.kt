package jp.matsuura.householdaccountapp.domain

import jp.matsuura.householdaccountapp.data.repository.CategoryRepository
import jp.matsuura.householdaccountapp.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke(categoryId: Int?): CategoryModel {
        return if (categoryId != null) {
            categoryRepository.getById(categoryId = categoryId)
        } else {
            categoryRepository.getFirstOfAll()
        }
    }
}