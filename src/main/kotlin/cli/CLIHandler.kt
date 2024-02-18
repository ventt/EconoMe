package cli


import cli.command.CommandHandler
import cli.command.commands.CreateCategoryCommand
import cli.command.commands.AddExpenseCommand
import cli.command.commands.AddIncomeCommand
import cli.command.commands.DeleteCategoryCommand
import cli.command.interfaces.Command

class CLIHandler(private val repositoryManager: RepositoryManager) {
    private val commands: List<Command> = listOf(
        CreateCategoryCommand(repositoryManager.getCategoryRepository()),
        DeleteCategoryCommand(repositoryManager.getCategoryRepository(), repositoryManager.getExpenseRepository(), repositoryManager.getIncomeRepository()),
        AddIncomeCommand(repositoryManager.getIncomeRepository(), repositoryManager.getCategoryRepository()),
        AddExpenseCommand(repositoryManager.getExpenseRepository(), repositoryManager.getCategoryRepository())
    )
    private val commandHandler = CommandHandler(commands)
    fun open() {
        println("Welcome to EconoMe")
        while (true) {
            print("> ")
            val input = readlnOrNull() ?: ""
            if (input == "exit") {
                repositoryManager.save()
                break
            }
            commandHandler.handleInput(input)
        }
    }
}