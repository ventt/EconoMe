package cli.command

import cli.command.interfaces.ListCommand
import common.Printer
import common.interfaces.Listable

class ListCommandHandler(commands: List<ListCommand>) {
    private val commands = commands.toList()

    fun handleInput(input: String, map: Map<Int, Listable>) {
        if (input == "help") {
            println("List mode Commands:")
            println("exit - Exit list mode")
            commands.forEach {
                Printer.printWarning("=====================================")
                println(it.printHelp())
            }
            Printer.printSuccess("=====================================")
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
            command?.setMap(map)
            command?.execute(input) ?: Printer.printError("Invalid command")
        }
    }

}