import java.io.File
import java.lang.StringBuilder

fun main(args : Array<String>) {
    val n = File("input.txt").readLines()[0].toInt()
    val a = IntArray(n)

    when (n) {
        1 -> a[0] = 1
        2 -> {
            a[0] = 1
            a[1] = 2
        }
        3 -> {
            a[0] = 2
            a[1] = 1
            a[2] = 3
        }
        else -> {
            a[1] = 1
            a[2] = 3
            for (step in 4..n)
                if ((step and 1) == 0) a[step - 1] = a[(step ushr 1) - 1]
                else {
                    a[step - 1] = a[step ushr 1]
                    a[step ushr 1] = step
                }
            for (i in 0 until (n ushr 1)) a[i] = i + i + 2

        }
    }

    val res = StringBuilder ("")
    a.forEach { res.append("$it ")}

    File("output.txt").bufferedWriter().use { it.write(res.substring(0, res.length - 1)) }
}