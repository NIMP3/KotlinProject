package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import data.ExpenseManager
import data.ExpenseRepositoryImpl
import getColorsTheme
import modules.movies.data.movies
import modules.movies.ui.DetailScreen
import modules.movies.ui.MoviesScreen
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpenseViewModel
import ui.ExpenseDetail
import ui.ExpensesScreen

@Composable
fun Navigation(navController: NavHostController) {
    val colors = getColorsTheme()
    //val viewModel = viewModel(modelClass = ExpenseViewModel::class) {
    //    ExpenseViewModel(ExpenseRepositoryImpl(ExpenseManager))
    //}
    
    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navController = navController,
        startDestination = "movies"
    ) {
        composable("home") {
            /*val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState) { expense ->
                navController.navigate("expenseDetail/${expense.id}")
            }*/
        }
        
        composable(
            route = "expenseDetail/{id}",
            arguments = listOf(navArgument("id"){type = NavType.LongType })
        ) { backStackEntry ->
            /*val id = backStackEntry.arguments?.getLong("id")
            val expense = id?.let { expenseId -> viewModel.getExpenseById(expenseId) }
            ExpenseDetail(expense = expense, categories = viewModel.getCategories()) { expense ->
                viewModel.editExpense(expense)
                navController.popBackStack()
            }*/
        }

        composable("addExpense") {
            /*ExpenseDetail(categories = viewModel.getCategories()) { expense ->
                viewModel.addExpense(expense)
                navController.popBackStack()
            }*/
        }

        composable("movies") {
            MoviesScreen(){ movie ->
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