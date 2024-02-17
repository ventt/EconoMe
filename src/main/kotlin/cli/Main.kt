package org.example.cli

import cli.CLIHandler
import cli.RepositoryManager

fun main() {
    val repositoryManager = RepositoryManager()
    val cLIHandler = CLIHandler(repositoryManager)

    cLIHandler.open()

}