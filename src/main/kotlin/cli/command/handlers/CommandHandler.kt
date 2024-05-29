package cli.command.handlers

import cli.command.interfaces.Command

class CommandHandler(commands: List<Command>) : BaseCommandHandler<Command>(commands) {

    override fun execute(command: Command, input: String) {
        command.execute(input)
    }
}