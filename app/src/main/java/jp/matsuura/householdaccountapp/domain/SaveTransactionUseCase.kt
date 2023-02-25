package jp.matsuura.householdaccountapp.domain

import jp.matsuura.householdaccountapp.data.repository.TransactionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(categoryId: Int, moneyAmount: Int) {
        transactionRepository.insert(categoryId = categoryId, moneyAmount = moneyAmount)
    }
}