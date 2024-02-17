package cli.command.interfaces

interface Command {
    fun matches(input: String): Boolean
    fun execute(input: String)
    fun validate(input: String): Boolean
    fun printHelp(): String

}