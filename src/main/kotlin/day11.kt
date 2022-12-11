import java.io.File

data class Monkey(
    val id:Int,
    var items:MutableList<Int>,
    val operation: List<String>,
    val mod:Int,
    val toTrue:Int,
    val toFalse:Int){
    var inspectedItems = 0
}

infix fun Monkey.inspects(item:Int) :Int {
    this.inspectedItems += 1
    val n = if (operation[1] == "old") item else operation[1].toInt()
    val new:ULong = if (operation[0] == "*") item.toULong() * n.toULong() else item.toULong() + n.toULong()
//    return new/3 //part1
    return (new%N.toULong()).toInt()
}

var N = 0

fun main() {
    val file = "11.input"
//    val file = "11.test"

    val regex =
"""Monkey (\d+):
  Starting items: (.*)
  Operation: new = old(.*)
  Test: divisible by (\d+)
    If true: throw to monkey (\d+)
    If false: throw to monkey (\d+)""".toRegex()


    val monkeys = File("src/main/resources/$file").readText()
        .split("\n\n").map {
            val (id, items, op, mod, toTrue, toFalse) = regex.find(it)!!.destructured
            Monkey(
                id.toInt(),
                items.split(',').map { it.trim().toInt() }.toMutableList(),
                op.trim().split(' '),
                mod.toInt(),
                toTrue.toInt(),
                toFalse.toInt()
            )
        }

//    val rounds = 20 // part1
    val rounds = 10000
    N = monkeys.map { it.mod}.fold(1){ a,b -> a*b}
    println(N)


    for (r in 1..rounds) {
        monkeys.forEach { monkey ->
            for (i in monkey.items) {
                var item = monkey inspects i
                item %= N
                var to = monkey.toFalse
                if (item % monkey.mod == 0) {
                    to = monkey.toTrue
                }
                monkeys[to].items.add(item)

            }
            monkey.items = mutableListOf<Int>()
        }
    }

    val part1:ULong = monkeys.map { it.inspectedItems }.sorted().takeLast(2).fold(1UL){ a,b -> a*b.toULong()}
    println(part1)

    println(monkeys.map {it.inspectedItems} )
}