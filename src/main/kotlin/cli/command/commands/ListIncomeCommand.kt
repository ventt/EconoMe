package cli.command.commands

import cli.CLIListHandler
import cli.RepositoryManager
import cli.command.interfaces.Command
import common.Printer

class ListIncomeCommand(private val repositoryManager: RepositoryManager) : Command {
    override fun matches(input: String): Boolean {
        return input.startsWith("list incomes")
    }

    override fun execute(input: String) {
        val cliListHandler = CLIListHandler(repositoryManager, "income")
        cliListHandler.open()
    }

    override fun printHelp(){
        Printer.printHelp(
            "list incomes",
            "Lists all incomes.",
            "list incomes",
            "")
    }
}