import java.io.File
import kotlin.math.abs

data class Point(var x: Int, var y: Int)

fun Point(p: Point) = Point(p.x, p.y)

fun printSnake (set: List<Point>) {
    val minW =  set.map { it.x }.min()
    val maxW =  set.map { it.x }.max()
    val minH = set.map { it.y }.min()
    val maxH = set.map { it.y }.max()

    for (y in minH ..maxH){
        for (x in minW..maxW+1) {
            if (x==0 && y == 0)
                print('s')
            else if (set.contains(Point(x, y)))
                print('#')
            else
                print('.')
        }
        println()
    }
}

fun unary(n: Int) :Int {
    if (n > 0 ) return 1
    if (n < 0) return -1
    return 0
}

fun stepTail(t: Point, h:Point): Point {
    val diffX = h.x - t.x
    val diffY = h.y - t.y

    return Point(t.x + unary(diffX) , t.y + unary(diffY))
}

fun distance(a: Point, b: Point): Int {
    return listOf(abs(a.x - b.x), abs(a.y - b.y)).max()
}

fun move( steps: Int, stepFun: (Point) -> Point) {
    for (s in 0 until steps) {
        nodes[0] = stepFun(nodes[0])
        histNodes[0].add(Point(nodes[0]))

        // adjust nodes to the movement of head
        for (i in 1 until maxNodes) {
            if (distance(nodes[i], nodes[i-1]) > 1) {
                nodes[i] = stepTail(nodes[i],nodes[i-1])
            }
            histNodes[i].add(Point(nodes[i]))
        }
    }
}
fun start(instructions:List<Pair<Char,Int>>) {
    for ((dir, steps) in instructions) {
        when (dir) {
            'R' -> move(steps) { Point(it.x + 1, it.y) }
            'L' -> move(steps) { Point(it.x - 1, it.y) }
            'U' -> move(steps) { Point(it.x, it.y - 1) }
            'D' -> move(steps) { Point(it.x, it.y + 1) }
        }
    }
}

fun part (n: Int, instructions:List<Pair<Char,Int>>): Int {
    nodes = mutableListOf<Point>()
    histNodes = mutableListOf<MutableList<Point>>()
    maxNodes = n

    for (i in 0 until maxNodes) {
        nodes.add(Point(0, 0))
        histNodes.add(mutableListOf(Point(0, 0)))
    }

    start(instructions)

    return histNodes.last().toSet().size
}


var nodes = mutableListOf<Point>()
var histNodes = mutableListOf<MutableList<Point>>()
var maxNodes = 0

fun main() {
    val file = "9.input"
//    val file = "9.2.test"
//    val file = "9.test"


    val instructions = File("src/main/resources/$file")
        .readLines()
        .map {
            val (dir, n) = it.split(' ')
            Pair(dir.first(), n.toInt())
        }

    val part1 = part(2, instructions)
    val part2 = part(10, instructions)

    println(part1)
    println(part2)
}