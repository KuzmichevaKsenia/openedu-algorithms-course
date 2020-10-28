import java.io.File

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val num = lines[0].toInt()
    val array = lines[1].split(" ").map(String::toInt).toTypedArray()
    var res = "1 "

    for (i in 1 until num) {
        var index = i
        for (j in i downTo 1) {
            if (array[j] < array[j - 1]) {
                index = j - 1
                array[j] = array[index].also { array[index] = array[j] }
            }
        }
        res += "${index + 1} "
    }
    res += "\n"
    array.forEach { res += "$it " }

    File("output.txt")
        .bufferedWriter()
        .use { it.write(res.substring(0, res.length - 1)) }
}