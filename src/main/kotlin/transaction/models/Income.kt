package transaction.models

import category.models.Category
import common.Color
import common.Date
import common.DateUtils
import common.UniqueIdGenerator
import kotlinx.serialization.Serializable

@Serializable
data class Income(
    var amount: Double,
    var date: Date = DateUtils.now(),
    override var category: Category? = null,
    var description: String = "",
    override var id: String = UniqueIdGenerator.generate()
    ): FinancialTransaction {
    override fun type(): String {
        return "Income"
    }

    override fun print(): String {
        return "${Color.INCOME.code}Income${Color.RESET.code}: $amount, $date, $category, $description"
    }
}
