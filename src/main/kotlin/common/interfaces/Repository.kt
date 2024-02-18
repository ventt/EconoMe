package common.interfaces

interface Repository<T> {
    fun saveToJson()
    fun loadFromJson()
    fun create(item: T): T
    fun read(id: String): T?
    fun getAll(): List<T>
    fun update(item: T): T
    fun delete(id: String): Boolean

    fun save()
}