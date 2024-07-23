package ui.menu.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import getColorsTheme
import ui.menu.data.Project


@Composable
fun MenuView() {
    val projects = Project.createProjectList()

    Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
        HeaderMenu()
        MenuBody(modifier = Modifier.weight(1f), projects)
    }
}

@Composable
fun HeaderMenu() {
    val colors = getColorsTheme()
    
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = "https://www.jetbrains.com/_assets/www/kotlin-multiplatform/parts/sections/head/hero-shape.41226a16aa9674fbb2f397f143af121c.jpg",
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(100.dp).border(1.dp, colors.logoBorderColor, RoundedCornerShape(50)).padding(16.dp)
        )
        
        Text(
            text = "Kotlin Multiplatform Test Project",
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = colors.logoBorderColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp, top = 12.dp)
        )
        
        Text(
            text = "This project was created for testing and learning about Kotlin Multiplatform",
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

@Composable
fun MenuBody(modifier: Modifier, projects: List<Project>) {
    val colors = getColorsTheme()

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Projects",
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            color = colors.textColor,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items (projects) { project ->
                ProjectCardView(project)
            }
        }
    }
}