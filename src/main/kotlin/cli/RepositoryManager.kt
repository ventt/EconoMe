package cli

import category.repository.CategoryRepository
import transaction.repository.ExpenseRepository
import transaction.repository.IncomeRepository

class RepositoryManager {
    private val categoryRepository = CategoryRepository()
    private val incomeRepository = IncomeRepository()
    private val expenseRepository = ExpenseRepository()

    fun getCategoryRepository(): CategoryRepository {
        return categoryRepository
    }
    fun getIncomeRepository(): IncomeRepository {
        return incomeRepository
    }
    fun getExpenseRepository(): ExpenseRepository {
        return expenseRepository
    }
    fun save() {
        categoryRepository.save()
        incomeRepository.save()
        expenseRepository.save()
    }
}