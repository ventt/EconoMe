package cli.command.commands

import cli.CLIListHandler
import cli.RepositoryManager
import cli.command.interfaces.Command
import common.Printer

class ListExpensesCommand(private val repositoryManager: RepositoryManager) : Command{
    override fun matches(input: String): Boolean {
        return input.startsWith("list expenses")
    }

    override fun execute(input: String) {
        val cliListHandler = CLIListHandler(repositoryManager, "expense")
        cliListHandler.open()
    }

    override fun printHelp(){
        Printer.printHelp(
            "list expenses",
            "Lists all expenses.",
            "list expenses",
            ""
        )
    }
}