import java.io.File

fun String.findMarker(n:Int) : Int{
    return this.windowed(n, 1)
        .mapIndexed{ index, s ->  Pair(index,s) }
        .find { it.second.chunked(1).toSet().size == n }!!.first + n
}

fun main() {
    val input = File("src/main/resources/6.input").readText().trim()

    val part1 = input.findMarker(4)
    val part2 = input.findMarker(14)

    println(part1)
    println(part2)
}