import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min
import java.lang.StringBuilder

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val num = lines[0].toInt()
    val array = lines[1].split(" ").map(String::toInt).toIntArray().withIndex().toMutableList()
    val writer = File("output.txt").bufferedWriter()

    for (i in 1 until num) {
        for (j in i downTo 1) {
            if (array[j].value < array[j - 1].value) {
                array[j] = array[j - 1].also { array[j - 1] = array[j] }
            }
        }
    }
    array.forEachIndexed { newIndex, (oldIndex, _) ->
        writer.write("Swap elements at indices ${min(oldIndex, newIndex) + 1} and ${max(oldIndex, newIndex) + 1}.\n")
    }

    writer.write("No more swaps needed.\n")
    val res = StringBuilder ("")
    array.forEach { res.append("${it.value} ")}
    writer.use { it.write(res.substring(0, res.length - 1)) }
}
