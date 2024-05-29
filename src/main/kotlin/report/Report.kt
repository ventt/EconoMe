package report

import common.interfaces.Listable
import report.models.Summary
import transaction.models.Expense
import transaction.models.Income

fun calculateSummary(transactions: List<Listable>): Summary {
    var netAmount = 0.0
    var totalTransactions = 0

    for (transaction in transactions) {
        when (transaction) {
            is Expense -> netAmount -= transaction.amount
            is Income -> netAmount += transaction.amount
        }
        totalTransactions++
    }

    return Summary(netAmount, totalTransactions)
}