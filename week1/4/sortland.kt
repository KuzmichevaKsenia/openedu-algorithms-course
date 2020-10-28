import java.io.File

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val num = lines[0].toInt()
    val array = lines[1].split(" ").map(String::toFloat)
    val sortArray = array.toFloatArray()
    sortArray.sort()
    File("output.txt")
        .bufferedWriter()
        .use { it.write("${array.indexOf(sortArray[0]) + 1} ${array.indexOf(sortArray[num / 2]) + 1} ${array.indexOf(sortArray[num - 1]) + 1}") }
}