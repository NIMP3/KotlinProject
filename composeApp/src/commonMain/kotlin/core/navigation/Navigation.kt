package core.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import data.ExpenseManager
import data.ExpenseRepositoryImpl
import getColorsTheme
import modules.movies.data.movies
import modules.movies.ui.DetailScreen
import modules.movies.ui.MoviesScreen
import modules.movies.ui.MoviesViewModel
import presentation.ExpenseViewModel
import ui.ExpenseDetail
import ui.ExpensesScreen

@Composable
fun Navigation(navController: NavHostController) {
    val colors = getColorsTheme()
    val viewModel = viewModel { ExpenseViewModel(ExpenseRepositoryImpl(ExpenseManager)) }
    val moviesViewModel = viewModel { MoviesViewModel() }
    
    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navController = navController,
        startDestination = "movies"
    ) {
        composable("home") {
            val uiState by viewModel.uiState.collectAsState()
            ExpensesScreen(uiState) { expense ->
                navController.navigate("expenseDetail/${expense.id}")
            }
        }
        
        composable(
            route = "expenseDetail/{id}",
            arguments = listOf(navArgument("id"){type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            val expense = id?.let { expenseId -> viewModel.getExpenseById(expenseId) }
            ExpenseDetail(expense = expense, categories = viewModel.getCategories()) { expense ->
                viewModel.editExpense(expense)
                navController.popBackStack()
            }
        }

        composable("addExpense") {
            ExpenseDetail(categories = viewModel.getCategories()) { expense ->
                viewModel.addExpense(expense)
                navController.popBackStack()
            }
        }

        composable("movies") {
            MoviesScreen(moviesViewModel){ movie ->
                navController.navigate("movieDetail/${movie.id}")
            }
        }

        composable(
            route = "movieDetail/{id}",
            arguments =  listOf(navArgument("id"){ type = NavType.IntType})
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            DetailScreen(movie = movies.first { it.id == id }) {
                navController.popBackStack()
            }
        }
    }
}