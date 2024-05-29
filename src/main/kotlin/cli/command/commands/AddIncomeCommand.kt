package cli.command.commands

import category.repository.CategoryRepository
import cli.command.interfaces.Command
import common.ArgsParser
import common.DateUtils
import common.Printer
import transaction.models.Income
import transaction.repository.IncomeRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddIncomeCommand(private val incomeRepository: IncomeRepository, private val categoryRepository: CategoryRepository): Command {
    override fun matches(input: String): Boolean {
        return input.startsWith("add income")
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

        Printer.printCreatingEntity("Adding income transaction: amount = $amount, description = $description, category = $category, date = ${dateTime}")
        try {
            incomeRepository.create(Income(amount, DateUtils.fromLocalDateTime(dateTime), category, description))
            Printer.printSuccess("Income transaction created successfully")
        } catch (e: IllegalArgumentException) {
            e.message?.let { Printer.printError(it) }
        }
    }

    override fun printHelp(){
        Printer.printHelp("add income", "Creates an income transaction.",
            "add income -a <amount>",
            "-d <description> -c <category> -t <date>")
    }
}