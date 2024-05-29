package cli.command.commands

import cli.CLIListHandler
import cli.RepositoryManager
import cli.command.interfaces.Command
import common.Printer

class ListCategoryCommand(private val repositoryManager: RepositoryManager): Command {
    override fun matches(input: String): Boolean {
        return input.startsWith("list categories")
    }

    override fun execute(input: String) {
        val cliListHandler = CLIListHandler(repositoryManager, "category")
        cliListHandler.open()
    }

    override fun printHelp(): Unit {
        Printer.printHelp("list categories", "Lists all categories.",
            "list categories", "")
    }
}