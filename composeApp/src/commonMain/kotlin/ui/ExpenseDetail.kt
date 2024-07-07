package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.SheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.TitleType
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
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)){
            ExpenseAmount(
                priceContent = price,
                keyboardController = keyboardController,
                onPriceChange = { price = it }
            )

            Spacer(Modifier.height(24.dp))

            ExpenseTypeSelector(
                categorySelected = categorySelected,
            ) {
                scope.launch { sheetState.show() }
            }

            Spacer(Modifier.height(24.dp))

            ExpenseDescritpion(
                descriptionContent = description,
                keyboardController = keyboardController,
                onDescritpionChange = { description = it }
            )

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth().height(60.dp).clip(RoundedCornerShape(15)),
                onClick = {
                    val expenseGenerated = Expense(
                        amount = price,
                        category = ExpenseCategory.valueOf(expenseCategory),
                        description = description)

                    val expenseFromEdit = expense?.id?.let { expenseGenerated.copy(id = it) }
                    onActionExpense(expenseFromEdit ?: expenseGenerated)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colors.purple,
                    contentColor = Color.White
                ),
                enabled = price != 0.0 && description.isNotBlank() && expenseCategory.isNotBlank()
            ) {
                val buttonText = if (expense != null) TitleType.EDIT.value else TitleType.ADD.value
                Text(text = buttonText )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
@Composable
private fun ExpenseDescritpion(
    descriptionContent: String,
    onDescritpionChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    var text by remember { mutableStateOf(descriptionContent) }
    val colors = getColorsTheme()

    Column {
        Text(
            text = "Description",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )

        TextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newText ->
                if (newText.length <= 200) {
                    text = newText
                    onDescritpionChange(newText)
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colors.textColor,
                backgroundColor = colors.backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )

        Divider(color = Color.Black, thickness = 2.dp)
    }
}

@Composable
private fun ExpenseTypeSelector(
    categorySelected: String,
    openBottomSheet: () -> Unit
) {
    val colors = getColorsTheme()

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            Text(
                text = "Expenses made for",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

            Text(
                text = categorySelected,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.textColor
            )
        }

        IconButton(
            modifier = Modifier.clip(RoundedCornerShape(35)).background(colors.arrowRoundColor),
            onClick = { openBottomSheet.invoke() }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Expense type button",
                tint = colors.textColor
            )
        }
    }
}

@Composable
private fun ExpenseAmount(
    priceContent: Double,
    onPriceChange: (Double) -> Unit,
    keyboardController: SoftwareKeyboardController?) {

    val colors = getColorsTheme()
    var text by remember { mutableStateOf("$priceContent") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Amount",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text,
                fontSize = 25.sp,
                color = colors.textColor,
                fontWeight = FontWeight.ExtraBold
            )

            TextField(
                value = text,
                modifier = Modifier.weight(1f),
                onValueChange = { newText ->
                    val numericText = newText.filter { it.isDigit() || it == '.'}
                    text = if (numericText.isNotEmpty() && numericText.count { it == '.' } <= 1 ) {
                        try {
                            val newValue = numericText.toDouble()
                            onPriceChange(newValue)
                            numericText
                        } catch (e: NumberFormatException) { "" }
                    } else {
                        onPriceChange(0.0)
                        ""
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colors.textColor,
                    backgroundColor = colors.backgroundColor,
                    focusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.ExtraBold)
            )

            Text(
                "USD",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray
            )
        }

        Divider(color = Color.Black, thickness = 2.dp)
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