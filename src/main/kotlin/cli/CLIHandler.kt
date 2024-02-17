package cli


import cli.command.CommandHandler
import cli.command.commands.CreateCategoryCommand
import cli.command.commands.DeleteCategoryCommand
import cli.command.interfaces.Command

class CLIHandler(repositoryManager: RepositoryManager) {
    private val commands: List<Command> = listOf(
        CreateCategoryCommand(repositoryManager.getCategoryRepository()),
        DeleteCategoryCommand(repositoryManager.getCategoryRepository())
    )
    private val commandHandler = CommandHandler(commands)
    fun open() {
        println("Welcome to EconoMe")
        while (true) {
            print("> ")
            val input = readlnOrNull() ?: ""
            if (input == "exit") {
                break
            }
            commandHandler.handleInput(input)
        }
    }

}