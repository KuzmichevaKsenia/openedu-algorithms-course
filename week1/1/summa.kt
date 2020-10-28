import java.io.File

fun main(args : Array<String>) {
    val numbers = File("input.txt")
        .readLines()[0]
        .split(" ")
        .map(String::toInt)

    File("output.txt")
        .bufferedWriter()
        .use {
            it.write(numbers.sum().toString())
        }
}