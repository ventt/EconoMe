package common

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Serializable
data class Date(val year: Int, val month: Int, val day: Int, val hour: Int, val minute: Int) {
    override fun toString(): String {
        return "$year-$month-$day $hour:$minute"
    }
    fun toLocalDateTime(): LocalDateTime {
        return LocalDateTime.of(year, month, day, hour, minute)
    }
}

object DateUtils {
    fun now(): Date {
        val now = LocalDateTime.now()
        return Date(now.year, now.monthValue, now.dayOfMonth, now.hour, now.minute)
    }
    fun fromLocalDateTime(localDateTime: LocalDateTime): Date {
        return Date(
            localDateTime.year,
            localDateTime.monthValue,
            localDateTime.dayOfMonth,
            localDateTime.hour,
            localDateTime.minute
        )
    }
    fun isValidDate(dateStr: String): Boolean {
        try {
            LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm"))
        } catch (e: DateTimeParseException) {
            return false
        }
        return true
    }
}
