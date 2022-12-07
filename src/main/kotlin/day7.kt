import java.io.File

data class Dir(val name: String, val parent: Dir?) {
    val dirs: MutableList<Dir> = mutableListOf()
    val files: MutableList<Int> = mutableListOf()
}

var dir_cache = mutableMapOf<String, Int>()

fun du(d: Dir): Int {
    if (dir_cache.containsKey(d.name)) return dir_cache[d.name]!!
    val s = d.files.sum() + d.dirs.map { du(it) }.sum()
    dir_cache[d.name] = s
    return s
}

fun main() {
    val input = File("src/main/resources/7.input").readText().trim()


    val cmds = input.split("\n").drop(1).map { it.replace("$", "").trim().split(" ") }


    val root = Dir("/", null)
    var cur_dir = root

    println(cur_dir)

    cmds.forEach {
        when (it[0]) {
            "ls" -> {}
            "dir" -> cur_dir.dirs.add(Dir(cur_dir.name + "/" + it[1], cur_dir))
            "cd" ->  cur_dir =
                if (it[1] == "..")
                    cur_dir.parent!!
                else
                    cur_dir.dirs.find { d -> d.name == cur_dir.name + "/" + it[1] }!!

            else -> cur_dir.files.add(it[0].toInt())
        }
    }

    du(root)
    println(dir_cache)
    val part1 = dir_cache.filter { (k, v) -> v <= 100000 }.values.sum()

    val unused_space = 70000000 - dir_cache["/"]!!
    val target = 30000000 - unused_space

    val part2 = dir_cache.filter { (_, v) -> v >= target }.values.min()



    println(part1)
    println(part2)
}

