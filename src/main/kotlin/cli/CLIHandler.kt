package cli


import cli.command.handlers.CommandHandler
import cli.command.commands.*
import cli.command.interfaces.Command

class CLIHandler(private val repositoryManager: RepositoryManager) {
    private val commands: List<Command> = listOf(
        CreateCategoryCommand(repositoryManager.getCategoryRepository()),
        DeleteCategoryCommand(repositoryManager.getCategoryRepository(), repositoryManager.getExpenseRepository(), repositoryManager.getIncomeRepository()),
        AddIncomeCommand(repositoryManager.getIncomeRepository(), repositoryManager.getCategoryRepository()),
        AddExpenseCommand(repositoryManager.getExpenseRepository(), repositoryManager.getCategoryRepository()),
        ListExpensesCommand(repositoryManager), ListIncomeCommand(repositoryManager),
        ListCategoryCommand(repositoryManager), ListTransactionCommand(repositoryManager),
        ListTransactionsByCategoryCommand(repositoryManager)
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