package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseManager
import data.ExpenseRepositoryImpl
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpenseViewModel
import ui.ExpenseDetail
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {
    val colors = getColorsTheme()
    val viewModel = viewModel(modelClass = ExpenseViewModel::class) {
        ExpenseViewModel(ExpenseRepositoryImpl(ExpenseManager))
    }
    
    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navigator = navigator,
        initialRoute = "home"
    ) {
        scene(route = "home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState) { expense ->
                navigator.navigate("expenseDetail/${expense.id}")
            }
        }
        
        scene(route = "expenseDetail/{id}") {
            val id = it.path<Long>("id")
            val expense = id?.let { id -> viewModel.getExpenseById(id) }
            ExpenseDetail(expense = expense) { expense ->
                viewModel.editExpense(expense)
                navigator.popBackStack()
            }
        }

        scene(route = "addExpense") {
            ExpenseDetail() { expense ->
                viewModel.addExpense(expense)
                navigator.popBackStack()
            }
        }
    }
}