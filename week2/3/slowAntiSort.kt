import java.io.File
import java.lang.StringBuilder

fun main(args : Array<String>) {
      val n = File("input.txt").readLines()[0].toInt()
      //начальный массив
      val array = IntArray(n)
      for (i in 0 until n) {
          array[i] = i + 1
      }
      //худший массив
      val worstArray = IntArray(n)
      //максимальное количество сравнений на начальном массиве
      var maxComparisonsNumber = 0
      //проверка всех перестановок
    do {
        val arrayToCheck = IntArray(n)
        for (i in 0 until n) {
            arrayToCheck[i] = array[i]
        }
        val comparisonsNumber = QSort(arrayToCheck, 0, n - 1)
        if (comparisonsNumber > maxComparisonsNumber) {
            for (i in 0 until n) {
                worstArray[i] = array[i]
            }
            maxComparisonsNumber = comparisonsNumber
        }
    } while (next(array, n))

    val res = StringBuilder ("")
    worstArray.forEach { res.append("$it ")}

    File("output.txt").bufferedWriter().use { it.write(res.substring(0, res.length - 1)) }
}

fun QSort (array : IntArray, left : Int, right : Int) : Int {
    var comparisonsNumber = 0
    val key = array[(left + right) / 2]
    var i = left
    var j = right
    do {
        comparisonsNumber +=2
        while (array[i] < key) {
            i++
            comparisonsNumber++
        }
        while (key < array[j]) {
            j--
            comparisonsNumber++
        }
        if (i <= j) {
            array[i] = array[j].also { array[j] = array[i] }
            i++
            j--
        }
    } while (i <= j)
    if (left < j) comparisonsNumber += QSort(array, left, j)
    if (i < right) comparisonsNumber += QSort(array, i, right)
    return comparisonsNumber
}

fun next(perm : IntArray, n : Int) : Boolean {
    var i = n - 1
    while (--i >= 0 && perm[i] > perm[i + 1]){}
    if (i == -1) return false
    var j = i + 1
    var k = n - 1
    while (j < k) {
        perm[j] = perm[k].also {perm[k] = perm[j]}
        j++
        k--
    }
    j = i + 1
    while (perm[j] < perm[i]) j++
    perm[i] = perm[j].also {perm[j] = perm[i]}
    return true
}