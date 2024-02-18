package cli.command

import cli.command.interfaces.Command
import common.Printer

class CommandHandler(commands: List<Command>) {
    private val commands = commands.toList()

    fun handleInput(input: String) {
        if (input == "help") {
            commands.forEach {
                Printer.printWarning("=====================================")
                println(it.printHelp())
            }
            return
        }
        if (input.startsWith("help")) {
            val specificHelp = input.substringAfter("help").trim()
            val command = commands.find { it.matches(specificHelp) }
            if (command != null) {
                println(command.printHelp())
            } else {
                Printer.printError("Invalid command")
            }

        } else {
            val command = commands.find { it.matches(input) }
            command?.execute(input) ?: Printer.printError("Invalid command")
        }
    }
}