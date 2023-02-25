package jp.matsuura.householdaccountapp.ui.input_money

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import jp.matsuura.householdaccountapp.R
import jp.matsuura.householdaccountapp.model.CalculatorType
import jp.matsuura.householdaccountapp.model.CategoryModel
import jp.matsuura.householdaccountapp.ui.home.CategoryItem
import jp.matsuura.householdaccountapp.ui.home.HomeScreenEvent

@Composable
fun InputMoneyScreen(
    viewModel: InputMoneyViewModel = hiltViewModel(),
    navController: NavController,
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is InputMoneyScreenEvent.SaveTransactionSuccess -> {
                    Toast.makeText(context, "${it.categoryName}:${it.totalMoney}円を登録しました。", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
                is InputMoneyScreenEvent.Error -> {
                    Toast.makeText(context, "エラーが発生しました。", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    val state by viewModel.uiState.collectAsState()
    InputMoneyScreen(
        state = state,
        onConfirmButtonClicked = {
            viewModel.onConfirmButtonClicked()
        }
    )
}

@Composable
fun InputMoneyScreen(
    state: InputMoneyScreenState,
    onConfirmButtonClicked: (Unit) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(modifier = Modifier.padding(bottom = 16.dp), text = "2023年2月15日(土)", fontSize = 16.sp)
        SelectedCategoryItem(category = state.selectedCategory)
        TotalMoneyItem(totalMoney = state.totalMoneyAmount)
        CalculatorItem()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(height = 48.dp),
            onClick = {
                onConfirmButtonClicked(Unit)
            }
        ) {
            Text(text = "確定", fontSize = 14.sp)
        }
    }
}

@Composable
fun SelectedCategoryItem(category: CategoryModel?) {
    val category = category ?: return
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(36.dp),
            painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
            contentDescription = ""
        )
        Text(modifier = Modifier.padding(start = 8.dp), text = category.categoryName, fontSize = 16.sp)
    }
}

@Composable
fun TotalMoneyItem(totalMoney: Int) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        text = totalMoney.toString(),
        textAlign = TextAlign.End,
        fontSize = 32.sp
    )
}

@Composable
fun CalculatorItem() {
    val items = listOf(
        CalculatorType.Number(number = "7"),
        CalculatorType.Number(number = "8"),
        CalculatorType.Number(number = "9"),
        CalculatorType.Division,
        CalculatorType.Number(number = "4"),
        CalculatorType.Number(number = "5"),
        CalculatorType.Number(number = "6"),
        CalculatorType.Multiplication,
        CalculatorType.Number(number = "1"),
        CalculatorType.Number(number = "2"),
        CalculatorType.Number(number = "3"),
        CalculatorType.Minus,
        CalculatorType.Number(number = "00"),
        CalculatorType.Number(number = "0"),
        CalculatorType.DecimalPoint,
        CalculatorType.Plus,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        content = {
            items(count = items.size) { index ->
                CellItem(
                    item = items[index],
                )
            }
        }
    )
}

@Composable
fun CellItem(item: CalculatorType) {
    val text = when (item) {
        is CalculatorType.Number -> {
            item.number
        }
        is CalculatorType.Plus -> {
            "+"
        }
        is CalculatorType.Minus -> {
            "-"
        }
        is CalculatorType.Multiplication -> {
            "×"
        }
        is CalculatorType.Division -> {
            "÷"
        }
        is CalculatorType.DecimalPoint -> {
            "."
        }
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(0.dp)
            )
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(0.dp)
            ),
        onClick = {}
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp)
        )
    }

}