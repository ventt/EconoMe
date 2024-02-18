package common

import java.util.UUID

object UniqueIdGenerator {
    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
