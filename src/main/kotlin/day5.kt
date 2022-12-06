import java.io.File

fun List<List<String>>.transpose(): List<List<String>> {
    val e = this[0].map { it.map { "" } }
    return this.fold(e) { acc, strings ->
        strings.zip(acc) { a, b -> b + listOf(a) }
    }

}

fun main() {
    val lines = File("src/main/resources/5.input").readLines()

    val stacks = lines
        .take(8)
        .map {
            it
                .subSequence(1, it.length)
                .windowed(1, 4, true)
        }
        .transpose()
        .map {
            it
                .reversed()
                .filter { it.isNotBlank() }
        }
        .map { it.toMutableList() }


    val regex = """move ([0-9]+) from (\d+) to (\d+)""".toRegex()
    val instructions = lines
        .slice(10 until lines.size)
        .map {
            val (a, b, c) = regex.find(it)!!.destructured
            listOf(a, b, c).map { it.toInt() }
        }


    val part1 = stacks
    val part2 = stacks.map { it.toMutableList() }

    instructions.forEach { (n, source, dest) ->
        for (i in 1..n) part1[dest - 1].add(part1[source - 1].removeLast())
    }

    instructions.forEach { (n, source, dest) ->
        var temp = mutableListOf<String>()
        for (i in 1..n) temp.add(part2[source - 1].removeLast())
        part2[dest - 1].addAll(temp.reversed())

    }

    println(part1
        .map { it.last() }
        .reduce { a, b -> a + b })

    println(part2
        .map { it.last() }
        .reduce { a, b -> a + b })

}