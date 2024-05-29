package cli.command.commands.listCommands

import cli.command.interfaces.Command
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
        print(index)
        if(index == null) {
            println("Error: -i (index) argument is missing or not a number.")
            return
        }
        map[index]?.let {
            expenseRepository.delete(it.id)
            println("Expense deleted successfully")
        } ?: println("Error: Expense not found")


    }

    override fun printHelp(): Unit {
        Printer.printHelp(
            "delete expense",
            "Deletes an expense transaction.",
            "delete expense -i <index>",
            ""
        )
    }
}