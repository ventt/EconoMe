package category.models

import common.UniqueIdGenerator
import common.interfaces.Listable
import kotlinx.serialization.Serializable

@Serializable
data class Category(val name: String, override var id: String = UniqueIdGenerator.generate()) : Listable {
    override fun print(): String {
        return "Category: $name"
    }
}
