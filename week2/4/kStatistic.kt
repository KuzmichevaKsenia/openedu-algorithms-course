import java.io.File
import java.lang.StringBuilder

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val line1 = lines[0].split(" ").map(String::toInt).toIntArray()
    val line2 = lines[1].split(" ").map(String::toInt).toIntArray()
    val n = line1[0]
    val k1 = line1[1] - 1
    val k2 = line1[2] - 1
    val A = line2[0]
    val B = line2[1]
    val C = line2[2]
    val a = IntArray(n)
    a[0] = line2[3]
    a[1] = line2[4]

    for (i in 2 until n) {
        a[i] = A * a[i - 2] + B * a[i - 1] + C
    }

    QSort(a, 0, n - 1, k1, k2)

    val res = StringBuilder ("")
    for (k in k1..k2)
        res.append("${a[k]} ")

    File("output.txt").bufferedWriter().use { it.write(res.substring(0, res.length - 1)) }
}

fun QSort (array : IntArray, left : Int, right : Int, k1 : Int, k2 : Int) {
    val key = array[(left + right) / 2]
    var i = left
    var j = right
    do {
        while (array[i] < key) {
            i++
        }
        while (key < array[j]) {
            j--
        }
        if (i <= j) {
            array[i] = array[j].also { array[j] = array[i] }
            i++
            j--
        }
    } while (i <= j)
    if (left < j)
        if (k1 in left..j || k2 in left..j || left >= k1 && j <= k2)
            QSort(array, left, j, k1, k2)
    if (i < right)
        if (k1 in i..right || k2 in i..right || i >= k1 && right <= k2)
            QSort(array, i, right, k1, k2)
}