import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.ExpensesScreen

@Composable
@Preview
fun App() {
    PreComposeApp {
        AppTheme {
            ExpensesScreen()
        }
    }
}