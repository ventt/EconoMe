package transaction.models

import category.models.Category
import common.Date
import common.DateUtils
import common.UniqueIdGenerator
import kotlinx.serialization.Serializable


@Serializable
data class Expense(
    var amount: Double,
    var date: Date = DateUtils.now(),
    override var category: Category? = null,
    var description: String = "",
    var id: String = UniqueIdGenerator.generate(),
): FinancialTransaction {
    override fun type(): String {
        return "Expense"
    }
}

