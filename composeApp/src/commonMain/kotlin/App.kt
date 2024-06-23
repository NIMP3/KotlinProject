import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import data.ExpenseManager
import data.ExpenseRepositoryImpl
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.ExpenseViewModel
import presentation.ExpensesUiState
import ui.ExpensesScreen

@Composable
@Preview
fun App() {
    PreComposeApp {

        val viewModel = viewModel(modelClass = ExpenseViewModel::class) {
            ExpenseViewModel(ExpenseRepositoryImpl(ExpenseManager))
        }
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        AppTheme {
            ExpensesScreen(uiState) {}
        }
    }
}