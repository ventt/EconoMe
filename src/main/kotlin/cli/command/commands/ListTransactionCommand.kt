package cli.command.commands

import cli.CLIListHandler
import cli.RepositoryManager
import cli.command.interfaces.Command
import common.Printer

class ListTransactionCommand(private val repositoryManager: RepositoryManager) : Command {
    override fun matches(input: String): Boolean {
        return input.startsWith("list transactions") && !input.contains("-c")
    }

    override fun execute(input: String) {
        val cliListHandler = CLIListHandler(repositoryManager, "transaction")
        cliListHandler.open()
    }

    override fun printHelp(): Unit {
        Printer.printHelp(
            "list transactions",
            "Lists all transactions.",
            "list transactions",
            ""
        )
    }
}