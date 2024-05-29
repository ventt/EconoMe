package common

import report.models.Summary

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
    fun printSortOptions() {
        println("${Color.MAGENTA.code}Sort options:${Color.RESET.code}")
        println("${Color.CYAN.code}Date ascending:${Color.BLUE.code} sort date asc${Color.RESET.code}")
        println("${Color.CYAN.code}Date descending:${Color.BLUE.code} sort date desc${Color.RESET.code}")
        println("${Color.CYAN.code}Amount ascending:${Color.BLUE.code} sort amount asc${Color.RESET.code}")
        println("${Color.CYAN.code}Amount descending:${Color.BLUE.code} sort amount desc${Color.RESET.code}")
    }
    fun printSummary(summary: Summary){
        if(summary.netAmount > 0){
            println("${Color.GREEN.code}Total amount: ${summary.netAmount}${Color.RESET.code}")
        }else{
            println("${Color.RED.code}Total amount: ${summary.netAmount}${Color.RESET.code}")
        }
        println("${Color.CYAN.code}Total transactions: ${summary.totalTransactions}${Color.RESET.code}")
    }
}