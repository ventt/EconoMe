package common

object Printer {
    fun printError(message: String) {
        println("${Color.RED.code}$message${Color.RESET.code}")
    }

    fun printSuccess(message: String) {
        println("${Color.GREEN.code}$message${Color.RESET.code}")
    }

    fun printWarning(message: String) {
        println("${Color.YELLOW.code}$message${Color.RESET.code}")
    }
}