package cli.command.commands

import category.models.Category
import category.repository.CategoryRepository
import cli.command.interfaces.Command
import common.Printer

class CreateCategoryCommand(private val categoryRepository: CategoryRepository): Command{
    override fun matches(input: String): Boolean {
        return input.startsWith("create category")
    }
    private fun validate(input: String): Boolean {
        if (!input.contains("-n") || input.substringAfter("-n").trim().isEmpty()) {
            Printer.printError("Error: Missing or incorrect '-n' flag usage. Correct format: create category -n \"Category Name\"")
            return false
        }
        return true
    }

    override fun execute(input: String) {
        if(!validate(input)) return

        val name = input.substringAfter("-n").trim()

        println("Creating category with name: $name")
        try {
            categoryRepository.create(Category(name))
            Printer.printSuccess("Category created successfully")
        } catch (e: IllegalArgumentException) {
            e.message?.let { Printer.printError(it) }
        }

    }
    override fun printHelp(): String = """
        Command: create category
        Description: Creates a new category with the specified name.
        Usage: create category -n <name>
    """.trimIndent()

}