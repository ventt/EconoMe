package common

enum class Color(val code: String) {
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    CYAN("\u001b[36m"),
    MAGENTA("\u001b[35m"),
    RESET("\u001b[0m"),
    EXPENSE("\u001b[31m"),
    INCOME("\u001b[32m");

}