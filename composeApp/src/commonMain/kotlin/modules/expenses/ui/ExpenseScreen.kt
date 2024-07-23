package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.ExpenseManager
import getColorsTheme
import model.Expense
import model.ExpenseCategory
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.ExpensesUiState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesScreen(uiState: ExpensesUiState, onExpenseClick: (expense: Expense) -> Unit) {
    val colors = getColorsTheme()

    Box(Modifier.fillMaxSize().background(colors.backgroundColor)) {
        LazyColumn(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            stickyHeader {
                Column(modifier = Modifier.background(Color.White).padding(top = 48.dp)) {
                    ExpensesTotalHeader(uiState.total)
                    AllExpensesHeader()
                }
            }
            items(uiState.expenses) { expense ->
                ExpenseItem(expense = expense, onExpenseClick = onExpenseClick)
            }
        }
    }

}

@Composable
fun ExpensesTotalHeader(total: Double) {
    Card(
        shape = RoundedCornerShape(20), colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(130.dp).padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "$$total",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Text(text = "USD",
                 modifier = Modifier.align(Alignment.CenterEnd),
                 color = Color.Gray, fontSize = 18.sp)
        }
    }
}

@Composable
fun AllExpensesHeader() {
    val colors = getColorsTheme()
    
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "All Expenses",
             modifier = Modifier.weight(1f),
             fontWeight = FontWeight.ExtraBold,
             fontSize = 20.sp,
             color = colors.textColor)
        
        Button(
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            onClick = {} ) {
            Text("View All")
        }
    }
}

@Composable
fun ExpenseItem(expense: Expense, onExpenseClick: (expense: Expense) -> Unit) {
    val colors = getColorsTheme()
    
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp).clickable { onExpenseClick(expense) },
        colors = CardDefaults.cardColors(containerColor = colors.colorExpenseItem),
        shape = RoundedCornerShape(20)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(35),
                color = colors.purple
            ) {
                Image(
                    modifier = Modifier.padding(10.dp),
                    imageVector = expense.icon,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Imagen Icon Expense Item"
                )
            }
            
            Column(
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text(
                    text = expense.category.name.uppercase(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = colors.textColor
                )
                Text(
                    text = expense.description,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }
            
            Text(
                text = "$ ${expense.amount}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colors.textColor
            )
        }
    }
}

@Preview
@Composable
fun ExpenseScreenPreview() {
    val uiState = ExpensesUiState(expenses = ExpenseManager.fakeExpenseList, total = 1025.0)
    ExpensesScreen(uiState){
        
    }
}