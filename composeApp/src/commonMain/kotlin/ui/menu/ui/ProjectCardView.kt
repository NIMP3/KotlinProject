package ui.menu.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import getColorsTheme
import ui.menu.data.Project

@Composable
fun ProjectCardView(project: Project) {
    val colors = getColorsTheme()
    
    Card(
        modifier = Modifier.size(300.dp).padding(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = project.resource,
                contentDescription = project.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(6.5f).fillMaxWidth())
            
            Column(
                modifier = Modifier.weight(3.5f).fillMaxWidth().padding(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = project.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textColor,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = project.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
        }
        
    }
}