import java.io.File

fun getCross(grid: List<List<Int>>, y: Int, x: Int): List<List<Int>> {
    val col = grid.map { it[x] }
    var right = listOf<Int>()
    var bottom = listOf<Int>()

    val left = grid[y].slice(0 until x).reversed()
    val top = col.slice(0 until y).reversed()

    if (x != grid[y].size - 1)
        right = grid[y].slice(x + 1 until grid[y].size)

    if (y != col.size - 1)
        bottom = col.slice(y + 1 until col.size)

    return listOf(left, right, top, bottom)
}

fun isVisible(grid: List<List<Int>>, y: Int, x: Int): Boolean {
    return getCross(grid, y, x).any { it.all { i -> i < grid[y][x] } }
}

fun score(grid: List<List<Int>>, y: Int, x: Int): Int {
    val points = mutableListOf<Int>()
    val house = grid[y][x]
    val ranges = getCross(grid, y, x)

    for (r in ranges) {
        var c = 0
        for (tree in r) {
            c += 1
            if (tree >= house) break
        }
        points.add(c)
    }

    return points.reduce { a, b -> a * b }
}

fun main() {
    val file = "8.input"
//    val file = "8.test"

    val grid = File("src/main/resources/$file").readLines().map { it.chunked(1).map { it.toInt() } }
    val part1 = grid.mapIndexed { y, row -> List(row.size) { x -> isVisible(grid, y, x) } }.flatten().count { it }
    val part2 = grid.mapIndexed { y, row -> List(row.size) { x -> score(grid, y, x) } }.flatten().max()

    println(part1)
    println(part2)
}

