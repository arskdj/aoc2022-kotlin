import java.io.File

fun main() {
    val file = "10.input"
//    val file = "10.test"

    val instructions = File("src/main/resources/$file")
        .readLines()
        .map{it.split(' ') }

    val cycles = mutableListOf<Int>()
    var x = 1

    instructions.forEach {
        when(it[0]) {
            "noop" -> cycles.add(x)
            "addx" -> {
                cycles.add(x)
                cycles.add(x)
                x += it[1].toInt()
            }
        }
    }

    val part1 = (19..219 step 40 ).toList().map { (it+1) * cycles[it] }.sum()
    println(part1)

    for (i in 0..239) {
        if (i%40==0) println()
        val spriteStart = (cycles[i] - 1) % 40
        val spriteEnd = (cycles[i] + 1) % 40
        val pixelRange = spriteStart..spriteEnd
        if (i%40 in pixelRange) print(9608.toChar())
        else print('.')
    }
}