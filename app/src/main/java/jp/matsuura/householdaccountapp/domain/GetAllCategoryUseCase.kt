package jp.matsuura.householdaccountapp.domain

import jp.matsuura.householdaccountapp.data.repository.CategoryRepository
import jp.matsuura.householdaccountapp.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke(): List<CategoryModel> {
        return categoryRepository.getAll()
    }
}