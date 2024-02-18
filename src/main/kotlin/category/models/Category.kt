package category.models

import common.UniqueIdGenerator
import kotlinx.serialization.Serializable

@Serializable
data class Category(val name: String, val id: String = UniqueIdGenerator.generate())
