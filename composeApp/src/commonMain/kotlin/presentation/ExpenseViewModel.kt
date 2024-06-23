package presentation

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val total: Double = 0.0
)
class ExpenseViewModel(private val repository: ExpenseRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState = _uiState.asStateFlow()
    private val allExpenses = repository.getAllExpenses()
    
    init {
        getAllExpenses()
    }
    
    private fun getAllExpenses() {
        viewModelScope.launch { updateState() }
    }
    
    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repository.addExpense(expense)
            updateState()
        }
    }
    
    fun editExpense(expense: Expense) {
        viewModelScope.launch {
            repository.editExpense(expense)
            updateState()
        }
    }
    
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
            updateState()
        }
    }
    
    fun getExpenseById(id: Long): Expense = allExpenses.first { it.id == id }
    
    private fun updateState() {
        _uiState.update { state ->
            state.copy(expenses = allExpenses, total = allExpenses.sumOf { it.amount })
        }
    }
}