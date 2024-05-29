package cli.command.commands.listCommands

import cli.command.interfaces.ListCommand
import common.ArgsParser
import common.Printer
import common.interfaces.Listable
import transaction.repository.IncomeRepository

class DeleteIncomeByIndexCommand(private val incomeRepository: IncomeRepository): ListCommand {
    private var map: Map<Int, Listable> = mapOf()
    override fun setMap(map: Map<Int, Listable>) {
        this.map = map
    }
    override fun matches(input: String): Boolean {
        return input.startsWith("delete income")
    }

    override fun execute(input: String) {
        val args = input.split(" ").drop(2)
        val argsMap = ArgsParser.parseArgs(args)

        val index = argsMap["-i"]?.toIntOrNull()
        if(index == null) {
            Printer.printError("-i (index) argument is missing or not a number.")
            return
        }
        Printer.printDeletingEntity("Deleting income transaction with index: $index")
        map[index]?.let {
            incomeRepository.delete(it.id).let { success ->
                if (success) {
                    Printer.printSuccess("Income deleted successfully")
                } else {
                    Printer.printWarning("Delete unsuccessful")
                }
            }
        } ?: Printer.printWarning("Income not found")
    }

    override fun printHelp(): Unit {
        Printer.printHelp(
            "delete income",
            "Deletes an income transaction.",
            "delete income -i <index>",
            ""
        )
    }
}