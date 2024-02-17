package common.interfaces

interface Repository<T> {
    fun saveToJson()
    fun loadFromJson()
    fun create(item: T): T
    fun read(identifier: String): T?
    fun update(item: T): T
    fun delete(identifier: String): Boolean
}