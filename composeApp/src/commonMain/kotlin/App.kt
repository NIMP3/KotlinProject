import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.TitleType
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.menu.ui.MenuView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    PreComposeApp {
        val colors = getColorsTheme()
        AppTheme {
            val navigator = rememberNavigator()
            val titleType = getTitle(navigator)

            MenuView()
            /*Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = titleType.value,
                                fontSize = 25.sp,
                                color = colors.textColor)
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = colors.backgroundColor
                        ),
                        navigationIcon = {
                            IconButton(onClick = {
                                navigator.popBackStack()
                            }) {
                                Icon(
                                    modifier = Modifier.padding(start = 16.dp),
                                    imageVector = if (titleType == TitleType.DASHBOARD) Icons.Default.Apps else Icons.Default.ArrowBackIosNew,
                                    tint = colors.textColor,
                                    contentDescription = if (titleType == TitleType.DASHBOARD) "Dashboard" else "Back"
                                )
                            }
                        }
                    )
                },
                floatingActionButton =  {
                    if(titleType == TitleType.DASHBOARD) {
                        FloatingActionButton(
                            containerColor = colors.addIconColor,
                            contentColor = Color.White,
                            onClick = { navigator.navigate("addExpense") }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                tint = Color.White,
                                contentDescription = "Floating icon"
                            )
                        }
                    }
                }
            ) {
                Navigation(navigator)
            }*/
        }
    }
}

@Composable
fun getTitle(navigator: Navigator): TitleType {
    val route = navigator.currentEntry.collectAsState(null).value?.route?.route ?: "home"
    return when(route) {
        "expenseDetail/{id}" -> TitleType.EDIT
        "addExpense" -> TitleType.ADD
        else -> TitleType.DASHBOARD
    }
}