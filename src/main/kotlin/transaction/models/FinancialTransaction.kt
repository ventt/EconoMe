package transaction.models

import category.models.Category
import common.interfaces.Listable

interface FinancialTransaction : Listable {
    var category: Category?
    fun type(): String
}