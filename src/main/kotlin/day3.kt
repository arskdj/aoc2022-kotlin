import java.io.File


fun getPriority(c: Char): Int {
    if (c.code >= 'a'.code) return c.code - 'a'.code + 1
    return c.code - 'A'.code + 27
}

fun findCommon(list: List<String>): Int {
    val c =  list
        .map{ it.toSet() }
        .reduce { acc, set -> acc.intersect(set) }
        .first()

    return getPriority(c)
}

fun main() {
    val bags = File("src/main/resources/3.input")
        .readText()
        .split("\n")
        .dropLast(1)

    val part1 = bags
        .map { it.chunked(it.length/2) }
        .sumOf { findCommon(it) }

    val part2 = bags
        .chunked(3)
        .sumOf { findCommon(it) }

    println(part1)
    println(part2)
}