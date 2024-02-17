package category.repository

import category.models.Category
import common.interfaces.Repository
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer
import java.io.File

class CategoryRepository : Repository<Category> {
    private val categories = mutableListOf<Category>()
    private val filePath = "categories.json"
    private val jsonFormat = Json { prettyPrint = true }

    init {
        loadFromJson()
    }

    override fun saveToJson() {
        val jsonString = jsonFormat.encodeToString(ListSerializer(Category.serializer()), categories)
        File(filePath).writeText(jsonString)
    }

    override fun loadFromJson() {
        val file = File(filePath)
        if (file.exists()) {
            val jsonString = file.readText()
            categories.clear()
            categories.addAll(jsonFormat.decodeFromString(ListSerializer(Category.serializer()), jsonString))
        }
    }

    override fun create(item: Category): Category {
        return categories.find { it.name == item.name }?.let {
            throw IllegalArgumentException("Category with name: ${item.name} already exists")
        } ?: item.also {
            categories.add(it)
            saveToJson()
        }
    }

    override fun read(identifier: String): Category? {
        return categories.find { it.name == identifier }
    }

    override fun delete(identifier: String): Boolean {
        val isRemoved = categories.removeIf { it.name == identifier }
        if (isRemoved) saveToJson()
        return isRemoved
    }

    override fun update(item: Category): Category {
        return categories.find { it.name == item.name }?.let {
            categories.remove(it)
            categories.add(item)
            saveToJson()
            item
        } ?: throw IllegalArgumentException("Category with name: ${item.name} does not exist")
    }
}
