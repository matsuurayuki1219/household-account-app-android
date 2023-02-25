package jp.matsuura.householdaccountapp.ui.input_money

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.householdaccountapp.domain.GetCategoryUseCase
import jp.matsuura.householdaccountapp.domain.SaveTransactionUseCase
import jp.matsuura.householdaccountapp.model.CalculatorType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InputMoneyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCategory: GetCategoryUseCase,
    private val saveTransaction: SaveTransactionUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(InputMoneyScreenState.initValue)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<InputMoneyScreenEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val categoryId: Int? = savedStateHandle["categoryId"]

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                _uiState.value = _uiState.value.copy(isProgressVisible = true)
                getCategory(categoryId = categoryId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isProgressVisible = false, selectedCategory = it)
            }.onFailure {
                Timber.d(it)
                _uiState.value = _uiState.value.copy(isProgressVisible = false)
            }
        }
    }

    fun onCalculatorClicked(value: CalculatorType) {
        val currentMoney = _uiState.value.totalMoneyAmount
        when (value) {
            is CalculatorType.Number -> {
                try {
                    if (value.number == "00") {
                        val total = if (currentMoney == 0) {
                            "0"
                        } else {
                            currentMoney.toString() + value.number
                        }
                        _uiState.value = _uiState.value.copy(totalMoneyAmount = total.toInt())
                    } else {
                        val total = if (currentMoney == 0) {
                            value.number
                        } else {
                            currentMoney.toString() + value.number
                        }
                        _uiState.value = _uiState.value.copy(totalMoneyAmount = total.toInt())
                    }
                } catch (e: NumberFormatException) {
                    Timber.d(e)
                    viewModelScope.launch {
                        _uiEvent.emit(InputMoneyScreenEvent.Error(e))
                    }
                }
            }
            is CalculatorType.Del -> {
                val text = currentMoney / 10
                _uiState.value = _uiState.value.copy(totalMoneyAmount = text)
            }
        }
    }

    fun onConfirmButtonClicked() {
        viewModelScope.launch {
            kotlin.runCatching {
                val model = _uiState.value.selectedCategory ?: throw IllegalArgumentException()
                val categoryId = model.id
                val totalMoney = _uiState.value.totalMoneyAmount
                _uiState.value = _uiState.value.copy(isProgressVisible = true)
                saveTransaction(categoryId = categoryId, moneyAmount = totalMoney)
            }.onSuccess {
                val model = _uiState.value.selectedCategory
                val categoryName = model!!.categoryName
                val totalMoney = _uiState.value.totalMoneyAmount
                _uiEvent.emit(InputMoneyScreenEvent.SaveTransactionSuccess(
                    categoryName = categoryName,
                    totalMoney = totalMoney,
                ))
            }.onFailure {
                Timber.d(it)
                _uiState.value = _uiState.value.copy(isProgressVisible = false)
                _uiEvent.emit(InputMoneyScreenEvent.Error(it))
            }
        }
    }
}