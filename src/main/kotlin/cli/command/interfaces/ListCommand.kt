package cli.command.interfaces

import common.interfaces.Listable

interface ListCommand : Command{
    fun setMap(map: Map<Int, Listable>)
}