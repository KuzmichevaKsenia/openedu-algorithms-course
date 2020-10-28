import java.io.File

fun main(args : Array<String>) {
    val lines = File("input.txt").readLines()
    val num = lines[0].toInt()
    val array = lines[1].split(" ").map(String::toInt).toIntArray()
    val writer = File("output.txt").bufferedWriter()

    writer.use { it.write(mergeInversesNumber(array, 0, num - 1).toString()) }
}

fun mergeInversesNumber(array : IntArray, begin : Int, end : Int) : Long {
    val n = end - begin + 1
    return if (n > 1) {
        val left = mergeInversesNumber(array, begin, begin + n / 2 - 1)
        val right = mergeInversesNumber(array, begin + n / 2, end)
        merge(array, begin, begin + n / 2, end) + left + right
    } else 0
}

fun merge(array : IntArray, leftBegin : Int, rightBegin : Int, rightEnd : Int) : Long {
    var res = 0L
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
            res += rightBegin - i
        }
    }

    i = leftBegin
    for (k in list) {
        array[i] = k
        i++
    }
    return res
}
