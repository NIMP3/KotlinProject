package model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class Expense(
    val id: Long = -1,
    val amount: Double,
    val category: ExpenseCategory,
    val description: String
) {
    val icon = category.icon
}

enum class ExpenseCategory(val icon: ImageVector) {
    Groceries(Icons.Default.FoodBank),
    Party(Icons.Default.PartyMode),
    Snacks(Icons.Default.Fastfood),
    Coffee(Icons.Default.Coffee),
    Car(Icons.Default.ElectricCar),
    House(Icons.Default.House),
    Other(Icons.Default.ViewCozy)
}