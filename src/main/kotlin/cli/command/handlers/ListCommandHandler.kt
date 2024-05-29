package cli.command.handlers

import cli.command.interfaces.ListCommand
import common.Printer
import common.interfaces.Listable

class ListCommandHandler(commands: List<ListCommand>) : BaseCommandHandler<ListCommand>(commands) {

    private var map: Map<Int, Listable> = emptyMap()

    fun handleInput(input: String, map: Map<Int, Listable>) {
        this.map = map
        super.handleInput(input)
    }

    override fun execute(command: ListCommand, input: String) {
        command.setMap(map)
        command.execute(input)
    }

    override fun printHelp(){
        Printer.printSuccess("List mode Commands:")
        super.printHelp()
        Printer.printSuccess("=====================================")
        Printer.printSortOptions()
        Printer.printSuccess("=====================================")
    }
}