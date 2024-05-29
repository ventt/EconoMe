package cli.command.commands

import category.repository.CategoryRepository
import cli.command.interfaces.Command
import common.ArgsParser
import common.DateUtils
import common.Printer
import transaction.models.Expense
import transaction.repository.ExpenseRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AddExpenseCommand(private val expenseRepository: ExpenseRepository, private val categoryRepository: CategoryRepository): Command {
    override fun matches(input: String): Boolean {
        return input.startsWith("add expense")
    }

    override fun execute(input: String) {
        val args = input.split(" ").drop(2)
        val argsMap = ArgsParser.parseArgs(args)

        val amount = argsMap["-a"]?.toDoubleOrNull()
        if (amount == null) {
            Printer.printError("-a (amount) argument is missing or not a number.")
            return
        }

        val description = argsMap["-d"] ?: "No description"
        val category = argsMap["-c"]?.let { categoryName ->
            categoryRepository.findByName(categoryName)
        }
        val dateString = argsMap["-t"]

        if (dateString != null && !DateUtils.isValidDate(dateString)) {
            Printer.printError("-t (date) is not in the correct format 'yyyy-MM-dd:HH-mm'.")
            return
        }
        var dateTime = LocalDateTime.now()
        if(dateString != null){
            dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm"))
        }

        Printer.printCreatingEntity("Adding expense transaction: amount = $amount, description = $description, category = $category, date = ${dateTime}")
        try {
            expenseRepository.create(Expense(amount, DateUtils.fromLocalDateTime(dateTime), category, description))
            Printer.printSuccess("Expense transaction created successfully")
        } catch (e: IllegalArgumentException) {
            e.message?.let { Printer.printError(it) }
        }
    }

    override fun printHelp(){
        Printer.printHelp("add expense", "Creates an expense transaction.",
            "add expense -a <amount> -d <description> -c <category> -t <date>",
            "-d <description> -c <category> -t <date>")
    }
}