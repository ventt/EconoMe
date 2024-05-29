package cli.command.commands

import category.repository.CategoryRepository
import category.service.CategoryService
import cli.command.interfaces.Command
import common.Printer
import transaction.repository.ExpenseRepository
import transaction.repository.IncomeRepository

class DeleteCategoryCommand(categoryRepository: CategoryRepository, expenseRepository: ExpenseRepository, incomeRepository: IncomeRepository): Command {
    private val categoryService = CategoryService(categoryRepository, expenseRepository, incomeRepository)
    override fun matches(input: String): Boolean {
        return input.startsWith("delete category")
    }

    override fun execute(input: String) {
        if(!validate(input)) return
        val name = input.substringAfter("-n").trim()
        Printer.printDeletingEntity("Deleting category with name: $name")
        try {
            if(categoryService.deleteCategoryAndRemoveFromTransactions(name)) {
                Printer.printSuccess("Category deleted successfully")
            } else {
                Printer.printError("Category not found")
            }
        } catch (e: IllegalArgumentException) {
            e.message?.let { Printer.printError(it) }
        }
    }

    private fun validate(input: String): Boolean {
        if(!input.contains("-n") || input.substringAfter("-n").trim().isEmpty()) {
            Printer.printError("Missing or incorrect '-n' flag usage. Correct format: delete category -n \"Category Name\"")
            return false
        }
        return true
    }

    override fun printHelp(){
        Printer.printHelp("delete category", "Deletes a category.",
            "delete category -n <name>", "")
    }

}