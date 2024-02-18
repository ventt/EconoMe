package common

object ArgsParser {
    fun parseArgs(args: List<String>): Map<String, String> {
        val argsMap = mutableMapOf<String, String>()
        var i = 0
        while (i < args.size) {
            val key = args[i]
            if (i + 1 < args.size && !args[i + 1].startsWith("-")) {
                argsMap[key] = args[i + 1]
                i += 2
            } else {
                argsMap[key] = ""
                i++
            }
        }
        return argsMap
    }
}