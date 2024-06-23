package data

import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepositoryImpl(private val expenseManager: ExpenseManager): ExpenseRepository {
    override fun getAllExpenses(): List<Expense> = expenseManager.fakeExpenseList

    override fun addExpense(expense: Expense) {
        expenseManager.addExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(expense)
    }

    override fun deleteExpense(expense: Expense) {
        expenseManager.deleteExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> = expenseManager.getCategories()

}