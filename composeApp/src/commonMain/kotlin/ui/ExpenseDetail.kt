package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import getColorsTheme
import model.Expense
import model.ExpenseCategory

@Composable
fun ExpenseDetail(expense: Expense? = null, onActionExpense: (expense: Expense) -> Unit) {
    val colors = getColorsTheme()
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        Button(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = colors.addIconColor
            ),
            onClick = {
                if (expense == null) {
                    val newExpense = Expense(
                        amount = 5689.0,
                        category = ExpenseCategory.Car,
                        description = "Mechanical workshop repairs"
                    )
                    onActionExpense(newExpense)
                }
                else {
                    val modifiedExpense = expense.copy(amount = expense.amount*2)
                    onActionExpense(modifiedExpense)
                }
            }
        ) {
            Text(if (expense == null) "ADD" else "EDIT")
        }
    }
}