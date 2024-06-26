package data

import model.Expense
import model.ExpenseCategory

object ExpenseManager {
    private var currentId = 1L
    val fakeExpenseList = mutableListOf(
        Expense(id = currentId++, amount = 70.0, category = ExpenseCategory.Groceries, description = "Weekly buy"),     
        Expense(id = currentId++, amount = 50.0, category = ExpenseCategory.Party, description = "Birthday celebration"),
        Expense(id = currentId++, amount = 20.0, category = ExpenseCategory.Snacks, description = "Movie snacks"),
        Expense(id = currentId++, amount = 10.0, category = ExpenseCategory.Coffee, description = "Morning coffee"),
        Expense(id = currentId++, amount = 100.0, category = ExpenseCategory.Car, description = "Car maintenance"),
        Expense(id = currentId++, amount = 200.0, category = ExpenseCategory.House, description = "Home improvement"),
        Expense(id = currentId++, amount = 30.0, category = ExpenseCategory.Other, description = "Miscellaneous expense"),
        Expense(id = currentId++, amount = 40.0, category = ExpenseCategory.Groceries, description = "Monthly grocery shopping"),
        Expense(id = currentId++, amount = 15.0, category = ExpenseCategory.Party, description = "Dinner party"),
        Expense(id = currentId++, amount = 5.0, category = ExpenseCategory.Snacks, description = "Snacks for road trip"),
        Expense(id = currentId++, amount = 50.0, category = ExpenseCategory.Coffee, description = "Coffee beans")
    )

    fun addExpense(expense: Expense) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }

    fun deleteExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id}
        fakeExpenseList.removeAt(index)
    }

    fun getCategories(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory.Groceries,
            ExpenseCategory.Party,
            ExpenseCategory.Snacks,
            ExpenseCategory.Coffee,
            ExpenseCategory.Car,
            ExpenseCategory.House,
            ExpenseCategory.Other
        )
    }
}