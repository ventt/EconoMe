package cli.command.commands

import cli.CLIListHandler
import cli.RepositoryManager
import cli.command.interfaces.Command
import common.ArgsParser
import common.Printer

class ListTransactionsByCategoryCommand(private val repositoryManager: RepositoryManager) : Command {
    override fun matches(input: String): Boolean {
        return input.startsWith("list transactions") && input.contains("-c")
    }

    override fun execute(input: String) {
        val args = input.split(" ").drop(2) // remove "list transactions"
        val argsMap = ArgsParser.parseArgs(args)

        val category = argsMap["-c"]
        val cliListHandler = CLIListHandler(repositoryManager, "categoryTransactions $category")
        cliListHandler.open()
    }

    override fun printHelp(): Unit {
        Printer.printHelp(
            "list transactions",
            "Lists all transactions of a category.",
            "list transactions -c <category>",
            "")
    }
}