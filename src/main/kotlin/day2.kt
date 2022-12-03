import java.io.File


fun next(i: Int): Int {
    return (i + 1).mod(3)
}

fun prev(i: Int): Int {
    return (i - 1).mod(3)
}

infix fun Int.beats(opponent:Int): Boolean {
    return this==next(opponent)
}

infix fun Int.ties(opponent:Int): Boolean {
    return this==opponent
}

fun getPoints(pair: List<Int>): Int {
    val (elf, me) = pair
    me beats  elf && return 6 + me + 1
    me ties   elf && return 3 + me + 1
    return 0 + me + 1
}

val outcomes = listOf(::prev, {it}, ::next)

fun getAction(pair: List<Int>): Int {
    val (elf, i) = pair
    return outcomes[i](elf)
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

    val part1 = plays.sumOf(::getPoints)
    val part2 = plays
        .map { listOf(it[0], getAction(it)) }
        .sumOf(::getPoints)

    println(part1)
    println(part2)
}
