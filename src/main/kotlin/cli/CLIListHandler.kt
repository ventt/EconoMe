package cli

import category.service.CategoryService
import cli.command.handlers.ListCommandHandler
import cli.command.commands.listCommands.DeleteExpenseByIndexCommand
import cli.command.commands.listCommands.DeleteIncomeByIndexCommand
import cli.command.interfaces.ListCommand
import common.Printer
import common.SortOption
import common.interfaces.Listable
import transaction.models.Expense
import transaction.models.Income
import report.calculateSummary
import java.time.LocalDateTime

class CLIListHandler(private val repositoryManager: RepositoryManager, private var type : String) {
    private var sortOption: SortOption = SortOption.DATE_DESC
    private val categoryService = CategoryService(repositoryManager.getCategoryRepository(),
        repositoryManager.getExpenseRepository(),
        repositoryManager.getIncomeRepository())
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
            if(input.startsWith("sort")){
                handleSortOptionInput(input.removePrefix("sort"))
            }else{
                listCommandHandler.handleInput(input, indexedList)
            }

        }
    }
    private fun printList() {
        indexedList.forEach { (index, listable) ->
            println("$index. ${listable.print()}")
        }
        Printer.printSummary(calculateSummary(indexedList.values.toList()))
    }
    private fun setExpenseList() {
        indexedList = listToMap(sortList(repositoryManager.getExpenseRepository().getAll()))
    }

    private fun setIncomeList() {
        indexedList = listToMap(sortList(repositoryManager.getIncomeRepository().getAll()))
    }

    private fun setCategoryList() {
        indexedList = listToMap(repositoryManager.getCategoryRepository().getAll())
    }
    private fun setTransactionList() {
        val allTransactions = repositoryManager.getIncomeRepository().getAll() + repositoryManager.getExpenseRepository().getAll()
        indexedList = listToMap(sortList(allTransactions))
    }

    private fun getTransactionListByCategory(categoryName: String) {
        val transactions = categoryService.getTransactionsByCategory(categoryName)
        indexedList = listToMap(sortList(transactions))
    }
    private fun listToMap(list: List<Listable>): Map<Int, Listable> {
        return list.mapIndexed { index, listable -> index + 1 to listable }.toMap()
    }

    private fun handleSortOptionInput(input: String) {
        sortOption = when (input.trim().lowercase()) {
            "date asc" -> SortOption.DATE_ASC
            "date desc" -> SortOption.DATE_DESC
            "amount asc" -> SortOption.AMOUNT_ASC
            "amount desc" -> SortOption.AMOUNT_DESC
            else -> SortOption.DATE_DESC
        }
    }
    private fun sortList(list: List<Listable>): List<Listable> {
        return when (sortOption) {
            SortOption.DATE_ASC -> list.sortedWith(compareBy {
                when (it) {
                    is Expense -> it.date.toLocalDateTime()
                    is Income -> it.date.toLocalDateTime()
                    else -> LocalDateTime.MIN
                }
            })
            SortOption.DATE_DESC -> list.sortedWith(compareByDescending {
                when (it) {
                    is Expense -> it.date.toLocalDateTime()
                    is Income -> it.date.toLocalDateTime()
                    else -> LocalDateTime.MIN
                }
            })
            SortOption.AMOUNT_ASC -> list.sortedWith(compareBy {
                when (it) {
                    is Expense -> it.amount
                    is Income -> it.amount
                    else -> 0.0
                }
            })
            SortOption.AMOUNT_DESC -> list.sortedWith(compareByDescending {
                when (it) {
                    is Expense -> it.amount
                    is Income -> it.amount
                    else -> 0.0
                }
            })
        }
    }
}