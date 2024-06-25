import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.TitleType
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PreComposeApp {
        val colors = getColorsTheme()
        AppTheme {
            val navigator = rememberNavigator()
            val title = getTitle(navigator)


            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        elevation = 0.dp,
                        title = {
                            Text(text = title.value,
                                fontSize = 25.sp,
                                color = colors.textColor)
                        },
                        backgroundColor = colors.backgroundColor,
                        navigationIcon = {
                            IconButton(onClick = {
                                navigator.popBackStack()
                            }) {
                                Icon(
                                    modifier = Modifier.padding(start = 16.dp),
                                    imageVector = if (title == TitleType.DASHBOARD) Icons.Default.Apps else Icons.Default.ArrowBackIosNew,
                                    tint = colors.textColor,
                                    contentDescription = if (title == TitleType.DASHBOARD) "Dashboard" else "Back"
                                )
                            }
                        }
                    )
                }
            ) {  }
        }
    }
}

@Composable
fun getTitle(navigator: Navigator): TitleType {
    val route = navigator.currentEntry.collectAsState(null).value?.route?.route ?: "home"
    return when(route) {
        "expenseDetail/{id}" -> TitleType.EDIT
        "addExpense/{id}" -> TitleType.ADD
        else -> TitleType.DASHBOARD
    }
}