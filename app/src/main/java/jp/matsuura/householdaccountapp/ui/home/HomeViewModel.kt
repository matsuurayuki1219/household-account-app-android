package jp.matsuura.householdaccountapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.householdaccountapp.domain.GetAllCategoryUseCase
import jp.matsuura.householdaccountapp.model.CategoryModel
import jp.matsuura.householdaccountapp.model.CategoryType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCategory: GetAllCategoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState.initValue)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<HomeScreenEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var categoryList: List<CategoryModel> = emptyList()

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                _uiState.value = _uiState.value.copy(isProgressVisible = true)
                getAllCategory()
            }.onSuccess { categories ->
                categoryList = categories
                _uiState.value = _uiState.value.copy(isProgressVisible = false)
                _uiState.value = _uiState.value.copy(category = categoryList.filter { it.categoryType == CategoryType.SPENDING })
            }.onFailure { throwable ->
                _uiState.value = _uiState.value.copy(isProgressVisible = false)
                Timber.d(throwable)
            }
        }
    }

    fun onTypeChanged(type: CategoryType) {
        _uiState.value = _uiState.value.copy(category = categoryList.filter { it.categoryType == type })
    }

    fun onItemClicked(categoryId: Int) {
        viewModelScope.launch {
            _uiEvent.emit(
                HomeScreenEvent.NavigateToInputScreen(
                    categoryId = categoryId,
                )
            )
        }
    }
}