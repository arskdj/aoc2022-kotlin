import java.io.File


fun main() {
    val bags = File("src/main/resources/3.input")
        .readText()
        .split("\n")
        .dropLast(1)

    val compartments = bags.map { it
        .chunked(it.length/2)
        }

    val part1 = compartments
        .map { it.map { it.toSet() } }
        .flatMap { it[0].intersect(it[1]) }
        .sumOf { getPriority(it) }

    val part2 = bags.chunked(3)
        .map { it.map { it.toSet() } }
        .flatMap { it[0].intersect(it[1]).intersect(it[2])}
        .sumOf { getPriority(it) }

    println(part1)
    println(part2)
}

fun getPriority(c: Char): Int {
    if (c.code >= 'a'.code) return c.code - 'a'.code + 1
    return c.code - 'A'.code + 27
}
