package cli

import category.service.CategoryService
import cli.command.handlers.ListCommandHandler
import cli.command.commands.listCommands.DeleteExpenseByIndexCommand
import cli.command.commands.listCommands.DeleteIncomeByIndexCommand
import cli.command.interfaces.ListCommand
import common.interfaces.Listable
import transaction.models.Expense

class CLIListHandler(private val repositoryManager: RepositoryManager, private var type : String) {
    private val categoryService = CategoryService(repositoryManager.getCategoryRepository(), repositoryManager.getExpenseRepository(), repositoryManager.getIncomeRepository())
    private fun setMap(){
        when {
            type == "expense" -> setExpenseList()
            type == "income" -> setIncomeList()
            type == "category" -> setCategoryList()
            type.contains("categoryTransactions") -> {
                val categoryName = type.split(" ")[1]
                getTransactionListByCategory(categoryName)
            }
            type == "transaction" -> setTransactionList()
        }
    }

    private var indexedList = mapOf<Int, Listable>()
    private val commands: List<ListCommand> = listOf(
        DeleteExpenseByIndexCommand(repositoryManager.getExpenseRepository()),
        DeleteIncomeByIndexCommand(repositoryManager.getIncomeRepository())
    )
    private val listCommandHandler = ListCommandHandler(commands)

    fun open() {
        while (true){
            setMap()
            printList()
            print("-> ")
            val input = readlnOrNull() ?: ""
            if (input == "exit") {
                break
            }
            listCommandHandler.handleInput(input, indexedList)
        }
    }
    private fun printList() {
        indexedList.forEach { (index, listable) ->
            println("$index. ${listable.print()}")
        }
    }
    private fun setExpenseList() {
        val sortedExpenses = repositoryManager.getExpenseRepository().getAll()
            .sortedWith(compareByDescending<Expense> { it.date }.thenByDescending { it.amount })
        indexedList = listToMap(sortedExpenses)
    }
    private fun setIncomeList() {
        indexedList = listToMap(repositoryManager.getIncomeRepository().getAll())
    }
    private fun setCategoryList() {
        indexedList = listToMap(repositoryManager.getCategoryRepository().getAll())
    }
    private fun setTransactionList() {
        indexedList = listsToMap(listOf(repositoryManager.getIncomeRepository().getAll(), repositoryManager.getExpenseRepository().getAll()))
    }
    private fun getTransactionListByCategory(categoryName: String) {
        indexedList = listToMap(categoryService.getTransactionsByCategory(categoryName))
    }
    private fun listToMap(list: List<Listable>): Map<Int, Listable> {
        return list.mapIndexed { index, listable -> index + 1 to listable }.toMap()
    }
    private fun listsToMap(lists: List<List<Listable>>): Map<Int, Listable> {
        return lists.flatten().mapIndexed { index, listable -> index + 1 to listable }.toMap()
    }
    private fun summaryOfTransactionsAmounts(transactions: List<Listable>): Double {
        // TODO: Implement this function
        return 0.0
    }
}