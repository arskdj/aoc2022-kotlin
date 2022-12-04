import java.io.File

fun main() {
    val pairs = File("src/main/resources/4.input").readLines().map {
        it.split(',').map {
            it.split('-').map { it.toInt() }
        }
    }

    val part1 = pairs.count { (a, b) -> a contains b || b contains a }
    val part2 = pairs.count { (a, b) -> a overlaps b || a contains b }

    println(part1)
    println(part2)
}

infix fun List<Int>.contains(range: List<Int>): Boolean {
    return this[0] <= range[0] && this[1] >= range[1]
}

infix fun List<Int>.overlaps(range: List<Int>): Boolean {
    return this[0] in range[0]..range[1] || this[1] in range[0]..range[1]
}