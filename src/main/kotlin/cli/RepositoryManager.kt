package cli

import category.repository.CategoryRepository

class RepositoryManager {
    private val categoryRepository = CategoryRepository()

    fun getCategoryRepository(): CategoryRepository {
        return categoryRepository
    }
}