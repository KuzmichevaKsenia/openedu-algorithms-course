import java.io.File

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val line1 = lines[0].split(" ").map(String::toInt).toIntArray()
    val n = line1[0]
    val k = line1[1]
    val array = lines[1].split(" ").map(String::toInt).toIntArray()

    for (i in 0 until k) {
        QSort(array, i, n - 1 - (n - 1 - i) % k, k)
    }

    File("output.txt").bufferedWriter().use { it.write(if (isSorted(array, n)) "YES" else "NO") }
}

fun isSorted (array : IntArray, n : Int) : Boolean {
    for (i in 1 until n)
        if (array[i] < array[i - 1])
            return false
    return true
}

fun QSort (array : IntArray, left : Int, right : Int, k : Int) {
    val parts = (right - left) / k
    val key = array[left + parts / 2 * k]
    var i = left
    var j = right
    do {
        while (array[i] < key) {
            i += k
        }
        while (key < array[j]) {
            j -= k
        }
        if (i <= j) {
            array[i] = array[j].also { array[j] = array[i] }
            i += k
            j -= k
        }
    } while (i <= j)
    if (left < j) QSort(array, left, j, k)
    if (i < right) QSort(array, i, right, k)
}