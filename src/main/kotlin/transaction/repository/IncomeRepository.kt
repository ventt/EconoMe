package transaction.repository


import common.UniqueIdGenerator
import common.interfaces.Repository
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer
import transaction.models.Income
import java.io.File

class IncomeRepository: Repository<Income> {
    private val incomes = mutableListOf<Income>()
    private val filePath = "incomes.json"
    private val jsonFormat = Json { prettyPrint = true }

    init {
        loadFromJson()
    }
    override fun saveToJson() {
        val jsonString = jsonFormat.encodeToString(ListSerializer(Income.serializer()), incomes)
        File(filePath).writeText(jsonString)
    }

    override fun loadFromJson() {
        val file = File(filePath)
        if (file.exists()) {
            val jsonString = file.readText()
            incomes.clear()
            incomes.addAll(jsonFormat.decodeFromString(ListSerializer(Income.serializer()), jsonString))
        }
    }

    override fun read(id: String): Income? {
        return incomes.find { it.id == id }
    }

    override fun getAll(): List<Income> {
        return incomes
    }

    override fun delete(id: String): Boolean {
        val isRemoved = incomes.removeIf { it.id == id}
        if (isRemoved) saveToJson()
        return isRemoved
    }

    override fun update(item: Income): Income {
        return incomes.find { it.id == item.id }?.let {
            incomes.remove(it)
            incomes.add(item)
            saveToJson()
            item
        } ?: throw IllegalArgumentException("Expense with id: ${item.id} does not exist")
    }

    override fun create(item: Income): Income {
        return item.also {
            it.id = UniqueIdGenerator.generate()
            incomes.add(it)
            saveToJson()
        }
    }
    override fun save() {
        saveToJson()
    }

}