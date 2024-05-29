package cli.command

import cli.command.interfaces.Command
import common.Printer

abstract class BaseCommandHandler<T : Command>(protected val commands: List<T>) {

    fun handleInput(input: String) {
        when {
            input == "help" -> printHelp()
            input.startsWith("help") -> printSpecificHelp(input)
            else -> executeCommand(input)
        }
    }

    open fun printHelp() {
        commands.forEach {
            Printer.printWarning("=====================================")
            it.printHelp()
        }
        Printer.printWarning("=====================================")
        Printer.printSuccess("exit - Exit the program")
    }

    private fun printSpecificHelp(input: String) {
        val specificHelp = input.substringAfter("help").trim()
        val command = commands.find { it.matches(specificHelp) }
        if (command != null) {
            println(command.printHelp())
        } else {
            Printer.printError("Invalid command")
        }
    }

    private fun executeCommand(input: String) {
        val command = commands.find { it.matches(input) }
        if (command != null) {
            execute(command, input)
        } else {
            Printer.printError("Invalid command")
        }
    }

    protected abstract fun execute(command: T, input: String)
}