import java.io.BufferedWriter
import java.io.File
import java.lang.StringBuilder

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val num = lines[0].toInt()
    val array = lines[1].split(" ").map(String::toInt).toIntArray()
    val writer = File("output.txt").bufferedWriter()

    mergeSort(array, 0, num - 1, writer)

    val res = StringBuilder ("")
    array.forEach { res.append("$it ")}
    writer.use { it.write(res.substring(0, res.length - 1)) }
}

fun mergeSort(array : IntArray, begin : Int, end : Int, writer : BufferedWriter) {
    val n = end - begin + 1
    if (n > 1) {
        mergeSort(array, begin, begin + n / 2 - 1, writer)
        mergeSort(array, begin + n / 2, end, writer)
        merge(array, begin, begin + n / 2, end, writer)
    }
}

fun merge(array : IntArray, leftBegin : Int, rightBegin : Int, rightEnd : Int, writer : BufferedWriter) {
    var i = leftBegin
    var j = rightBegin
    val list = ArrayList<Int>()
    while (i < rightBegin || j < rightEnd + 1) {
        if (j == rightEnd + 1 || (i < rightBegin && array[i] <= array[j])) {
            list.add(array[i])
            i++
        } else {
            list.add(array[j])
            j++
        }
    }

    i = leftBegin
    for (k in list) {
        array[i] = k
        i++
    }
    writer.write("${leftBegin + 1} ${rightEnd + 1} ${array[leftBegin]} ${array[rightEnd]}\n")
}
