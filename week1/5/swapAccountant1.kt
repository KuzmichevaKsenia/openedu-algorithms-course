import java.io.File
import java.lang.StringBuilder

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val num = lines[0].toInt()
    val array = lines[1].split(" ").map(String::toInt).toIntArray()
    val writer = File("output.txt").bufferedWriter()

    for (i in 1 until num) {
        for (j in i downTo 1) {
            if (array[j] < array[j - 1]) {
                array[j] = array[j - 1].also { array[j - 1] = array[j] }
                writer.write("Swap elements at indices $j and ${j + 1}.\n")
            }
        }
    }

    writer.write("No more swaps needed.\n")
    val res = StringBuilder ("")
    array.forEach { res.append("$it ")}
    writer.use { it.write(res.substring(0, res.length - 1)) }
}
