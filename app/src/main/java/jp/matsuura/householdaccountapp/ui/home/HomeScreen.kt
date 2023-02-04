package jp.matsuura.householdaccountapp.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jp.matsuura.householdaccountapp.model.CategoryModel
import jp.matsuura.householdaccountapp.model.CategoryType
import jp.matsuura.householdaccountapp.ui.theme.Purple40

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToInputScreen: (Int, String) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(true) {
        viewModel.uiEvent.collect {
            when (it) {
                is HomeScreenEvent.NavigateToInputScreen -> {
                    navigateToInputScreen(it.categoryId, it.categoryName)
                }
            }
        }
    }
    HomeScreen(
        state = state,
        onTypeChanged = { type ->
            viewModel.onTypeChanged(type = type)
        },
        onItemClicked = { categoryId, categoryName ->
            viewModel.onItemClicked(categoryId = categoryId, categoryName = categoryName)
        }
    )
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onTypeChanged: (CategoryType) -> Unit,
    onItemClicked: (Int, String) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxWidth().padding(24.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth().weight(1.0f, true).padding(10.dp),
                    onClick = {
                        onTypeChanged(CategoryType.SPENDING)
                    }
                ) {
                    Text(text = "支出")
                }
                Button(
                    modifier = Modifier.fillMaxWidth().weight(1.0f, true).padding(10.dp),
                    onClick = {
                        onTypeChanged(CategoryType.INCOME)
                    }
                ) {
                    Text(text = "収入")
                }
            }
            CategoryScreen(items = state.category, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun CategoryScreen(
    items: List<CategoryModel>,
    onItemClicked: (Int, String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        content = {
            items(count = items.size) { index ->
                CategoryItem(
                    categoryId = items[index].id,
                    categoryName = items[index].categoryName,
                    onItemClicked = onItemClicked,
                )
            }
        }
    )
}

@Composable
fun CategoryItem(
    categoryId: Int,
    categoryName: String,
    onItemClicked: (Int, String) -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(categoryId, categoryName) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawCircle(
                color = Purple40,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = size.minDimension / 4
            )
        }
        Text(text = categoryName, textAlign = TextAlign.Center)
    }
}