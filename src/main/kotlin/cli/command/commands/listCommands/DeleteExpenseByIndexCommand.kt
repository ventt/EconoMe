package cli.command.commands.listCommands

import cli.command.interfaces.ListCommand
import common.ArgsParser
import common.Printer
import common.interfaces.Listable
import transaction.repository.ExpenseRepository

class DeleteExpenseByIndexCommand(private val expenseRepository: ExpenseRepository): ListCommand {
    private var map: Map<Int, Listable> = mapOf()
    override fun setMap(map: Map<Int, Listable>) {
        this.map = map
    }
    override fun matches(input: String): Boolean {
        return input.startsWith("delete expense")
    }

    override fun execute(input: String) {
        val args = input.split(" ").drop(2)
        val argsMap = ArgsParser.parseArgs(args)

        val index = argsMap["-i"]?.toIntOrNull()
        if(index == null) {
            Printer.printError("-i (index) argument is missing or not a number.")
            return
        }
        Printer.printDeletingEntity("Deleting expense transaction with index: $index")
        map[index]?.let {
            expenseRepository.delete(it.id).let { success ->
                if (success) {
                    Printer.printSuccess("Expense deleted successfully")
                } else {
                    Printer.printWarning("Delete unsuccessful")
                }
            }
        } ?: Printer.printWarning("Expense not found")
    }

    override fun printHelp(){
        Printer.printHelp(
            "delete expense",
            "Deletes an expense transaction.",
            "delete expense -i <index>",
            ""
        )
    }
}