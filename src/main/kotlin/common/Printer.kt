package common

object Printer {
    fun printError(message: String) {
        println("${Color.RED.code}Error: $message${Color.RESET.code}")
    }

    fun printSuccess(message: String) {
        println("${Color.GREEN.code}$message${Color.RESET.code}")
    }

    fun printWarning(message: String) {
        println("${Color.YELLOW.code}$message${Color.RESET.code}")
    }
    fun printHelp(command: String, description: String, usage: String, optional: String) {
        println("${Color.BLUE.code}Command:${Color.RESET.code} $command")
        println("${Color.CYAN.code}Description:${Color.RESET.code} $description")
        println("${Color.MAGENTA.code}Usage:${Color.RESET.code} $usage")
        if (optional.isNotEmpty()) {
            println("${Color.YELLOW.code}Optional:${Color.RESET.code} $optional")
        }
    }
    fun printCreatingEntity(message: String) {
        println("${Color.CYAN.code}$message${Color.RESET.code}")
    }
    fun printDeletingEntity(message: String) {
        println("${Color.MAGENTA.code}$message${Color.RESET.code}")
    }
}