import java.io.File


fun getPriority(c: Char): Int {
    if (c.code >= 'a'.code) return c.code - 'a'.code + 1
    return c.code - 'A'.code + 27
}

fun findCommon(list: List<String>): Int {
    return list
        .map { it.toSet() }
        .reduce { acc, set -> acc.intersect(set) }
        .first()
        .let(::getPriority)
}

fun main() {
    val bags = File("src/main/resources/3.input").readLines()

    val part1 = bags
        .map { it.chunked(it.length/2) }
        .sumOf(::findCommon)

    val part2 = bags
        .chunked(3)
        .sumOf(::findCommon)

    println(part1)
    println(part2)
}