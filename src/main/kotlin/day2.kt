import java.io.File


fun next(i: Int): Int {
    return (i + 1).mod(3)
}

fun prev(i: Int): Int {
    return (i - 1).mod(3)
}

fun getPoints(pair: List<Int>): Int {
    val (elf, me) = pair

    if (me == prev(elf)) return 0 + me + 1  // L
    if (me == elf)       return 3 + me + 1  // D
    return 6 + me + 1                       // W
}

fun getAction(pair: List<Int>): Int {
    val (elf, outcome) = pair

    if (outcome == 0) return prev(elf)  // L
    if (outcome == 1) return elf        // D
    return next(elf)                    //W
}

fun main() {
    val plays = File("src/main/resources/2.input")
        .readText()
        .split("\n")
        .map {it.split(" ")}
        .dropLast(1)
        .map {
            listOf(
                it[0].first().code - 'A'.code,
                it[1].first().code - 'X'.code
            )
        }

    val part1 = plays.sumOf { getPoints(it) }
    val part2 = plays.map { listOf(it[0], getAction(it)) }.sumOf { getPoints(it) }

    println(part1)
    println(part2)
}
