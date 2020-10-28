import java.io.File

fun main(args : Array<String>) {
    val numbers = File("input.txt")
        .readLines()[0]
        .split(" ")
        .map(String::toLong)

    File("output.txt")
        .bufferedWriter()
        .use {
            it.write((numbers[0] + numbers[1] * numbers[1]).toString())
        }
}