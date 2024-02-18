package cli.command.commands

import cli.command.interfaces.Command

class DeleteExpenseCommand : Command {
    override fun matches(input: String): Boolean {
        return input.startsWith("delete expense")
    }

    override fun execute(input: String) {

    }

    override fun printHelp(): String {
        TODO("Not yet implemented")
    }
}