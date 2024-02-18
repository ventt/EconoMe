package category.service

import category.repository.CategoryRepository
import transaction.models.FinancialTransaction
import transaction.repository.ExpenseRepository
import transaction.repository.IncomeRepository

class CategoryService(private val categoryRepository: CategoryRepository, private val expenseRepository: ExpenseRepository, private val incomeRepository: IncomeRepository) {

    fun getTransactionsByCategory(categoryName: String): List<FinancialTransaction> {
        val transactionList = listOf(expenseRepository.getAll(), incomeRepository.getAll())
        return transactionList.flatMap { it.filter { it.category?.name == categoryName } }
    }
    fun deleteCategoryAndRemoveFromTransactions(categoryName: String): Boolean {
        if(categoryRepository.deleteByName(categoryName)){
            deleteCategoryFromTransactions(categoryName)
            return true
        }
        return false
    }

    private fun deleteCategoryFromTransactions(categoryName: String) {
        getTransactionsByCategory(categoryName).forEach {
                it.category = null
        }
    }


}