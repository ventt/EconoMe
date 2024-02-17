package transaction.models

import java.time.LocalDate

interface FinancialTransaction {
    val id: Int
    val amount: Double
    val date: LocalDate
    val description: String

    fun type(): String
}