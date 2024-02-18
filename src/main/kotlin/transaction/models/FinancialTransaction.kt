package transaction.models

import category.models.Category

interface FinancialTransaction {
    var category: Category?
    fun type(): String
}