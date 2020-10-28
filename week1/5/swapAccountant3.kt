import java.io.BufferedWriter
import java.io.File
import java.lang.StringBuilder

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val num = lines[0].toInt()
    val array = lines[1].split(" ").map(String::toInt).toIntArray()
    val writer = File("output.txt").bufferedWriter()

    quickSort(array, 0, num - 1, writer)

    writer.write("No more swaps needed.\n")
    val res = StringBuilder ("")
    array.forEach { res.append("$it ")}
    writer.use { it.write(res.substring(0, res.length - 1)) }
}

fun quickSort (array : IntArray, lo : Int, hi : Int, writer : BufferedWriter) {
    if (lo < hi) {
        val p = partition(array, lo, hi, writer)
        quickSort(array, lo, p - 1, writer)
        quickSort(array, p + 1, hi, writer)
    }
}

fun partition (array : IntArray, lo : Int, hi : Int, writer : BufferedWriter) : Int {
    val pivot = array[hi]
    var i = lo
    for (j in lo until hi) {
        if (array[j] <= pivot) {
            if (array[i] != array[j]) {
                array[i] = array[j].also { array[j] = array[i] }
                writer.write("Swap elements at indices ${Integer.min(i + 1, j + 1)} and ${Integer.max(i + 1, j + 1)}.\n")
            }
            i++
        }
    }
    if (array[i] != array[hi]) {
        array[i] = array[hi].also { array[hi] = array[i] }
        writer.write("Swap elements at indices ${Integer.min(i + 1, hi + 1)} and ${Integer.max(i + 1, hi + 1)}.\n")
    }
    return i
}