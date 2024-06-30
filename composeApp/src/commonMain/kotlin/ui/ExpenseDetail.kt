package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import getColorsTheme
import kotlinx.coroutines.launch
import model.Expense
import model.ExpenseCategory

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpenseDetail(expense: Expense? = null, categories: List<ExpenseCategory> = emptyList(), onActionExpense: (expense: Expense) -> Unit) {
    val colors = getColorsTheme()
    
    var price by remember { mutableStateOf(expense?.amount ?: 0.0) }
    var description by remember { mutableStateOf(expense?.description ?: "") }
    var expenseCategory by remember { mutableStateOf(expense?.category?.name ?: "") }
    var categorySelected by remember { mutableStateOf(expense?.category?.name ?: "Select a category") }
    var sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(sheetState.targetValue) {
        if(sheetState.targetValue == ModalBottomSheetValue.Expanded) {
            keyboardController?.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            CategoryBottomSheetContent(categories) {
                expenseCategory = it.name
                categorySelected = it.name
                scope.launch {
                    sheetState.hide()
                }
            }
        }
    ) {

    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.BottomCenter){
        Button(modifier = Modifier.fillMaxWidth().height(60.dp), colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = colors.addIconColor
        ), onClick = {
            scope.launch {
                sheetState.show()
            }
        }) {
            Text(text = "SHOW MODAL")
        }
    }
}

@Composable
private fun CategoryBottomSheetContent(categories: List<ExpenseCategory>, onCategorySelected: (ExpenseCategory) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
    ){
        items(categories) { category ->
            CategoryItem(category = category, onCategorySelected = onCategorySelected)
        }
    }
}

@Composable
private fun CategoryItem(category: ExpenseCategory, onCategorySelected: (ExpenseCategory) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onCategorySelected(category) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            modifier = Modifier.size(40.dp).clip(CircleShape),
            imageVector = category.icon,
            contentDescription = "Category icon",
            contentScale = ContentScale.Crop
        )
        Text(text = category.name)
    }
}