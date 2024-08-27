import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import core.navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navHostController = rememberNavController()
    Navigation(navHostController)
    /*
    PreComposeApp {
        val colors = getColorsTheme()
        AppTheme {
            val navigator = rememberNavigator()
            val titleType = getTitle(navigator)

            //MenuView()
            //MoviesScreen()
            /*Scaffold(
                modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing),
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
    }*/
}

/*@Composable
fun getTitle(navigator: Navigator): TitleType {
    val route = navigator.currentEntry.collectAsState(null).value?.route?.route ?: "home"
    return when(route) {
        "expenseDetail/{id}" -> TitleType.EDIT
        "addExpense" -> TitleType.ADD
        else -> TitleType.DASHBOARD
    }
}*/