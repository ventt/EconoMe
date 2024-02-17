package cli.command.commands

import category.repository.CategoryRepository
import cli.command.interfaces.Command
import common.Printer

class DeleteCategoryCommand(private val categoryRepository: CategoryRepository): Command {
    override fun matches(input: String): Boolean {
        return input.startsWith("delete category")
    }

    override fun execute(input: String) {
        // Must remove the category from transactions as well
        if(!validate(input)) return
        val name = input.substringAfter("-n").trim()
        println("Deleting category with name: $name")
        try {
            if(categoryRepository.delete(name)) {
                Printer.printSuccess("Category deleted successfully")
            } else {
                Printer.printError("Category not found")
            }
        } catch (e: IllegalArgumentException) {
            e.message?.let { Printer.printError(it) }
        }
    }

    override fun validate(input: String): Boolean {
        if(!input.contains("-n") || input.substringAfter("-n").trim().isEmpty()) {
            Printer.printError("Error: Missing or incorrect '-n' flag usage. Correct format: delete category -n \"Category Name\"")
            return false
        }
        return true
    }

    override fun printHelp(): String = """
        Command: delete category
        Description: Deletes a category with the specified name.
        Usage: delete category -n <name>
    """.trimIndent()

}