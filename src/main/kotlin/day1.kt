import java.io.File

fun main() {
    val elves = File("src/main/resources/1.input")
        .readText()
        .split("\n\n")
        .map {
            it
                .split("\n")
                .filter { it.isNotEmpty() }
                .map{ it.toInt() }
        }

    val sums = elves.map { it.sum() }
    val part1 = sums.max()
    val part2 = sums.sortedDescending().take(3).sum()

    println(part1)
    println(part2)
}
