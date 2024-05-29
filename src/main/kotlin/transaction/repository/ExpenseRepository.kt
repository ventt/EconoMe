package transaction.repository

import common.interfaces.Repository
import transaction.models.Expense
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer
import transaction.models.FinancialTransaction
import java.io.File

class ExpenseRepository: FinancialTransactionRepository {
    private val expenses = mutableListOf<Expense>()
    private val filePath = "expenses.json"
    private val jsonFormat = Json { prettyPrint = true }

    init {
        loadFromJson()
    }
    override fun saveToJson() {
        val jsonString = jsonFormat.encodeToString(ListSerializer(Expense.serializer()), expenses)
        File(filePath).writeText(jsonString)
    }

    override fun loadFromJson() {
        val file = File(filePath)
        if (file.exists()) {
            val jsonString = file.readText()
            expenses.clear()
            expenses.addAll(jsonFormat.decodeFromString(ListSerializer(Expense.serializer()), jsonString))
        }
    }

    override fun read(id: String): Expense? {
        return expenses.find { it.id == id }
    }

    override fun getAll(): List<Expense> {
        return expenses
    }

    override fun delete(id: String): Boolean {
        val isRemoved = expenses.removeIf { it.id == id }
        if (isRemoved) saveToJson()
        return isRemoved
    }

    override fun update(item: FinancialTransaction): FinancialTransaction {
        return expenses.find { it.id == item.id }?.let {
            expenses.remove(it)
            expenses.add(item as Expense)
            saveToJson()
            item
        } ?: throw IllegalArgumentException("Expense with id: ${item.id} does not exist")
    }

    override fun create(item: FinancialTransaction): FinancialTransaction {
        return item.also {
            expenses.add(it as Expense)
            saveToJson()
        }
    }
    override fun save() {
        saveToJson()
    }
}