import java.io.File
import java.util.*

fun main(args : Array<String>) {
    val stack = Stack<Int>()
    File("input.txt").readLines()[1].split(" ").forEach {
        when (it) {
            "+" -> stack.push(stack.pop() + stack.pop())
            "-" -> stack.push(-1 * (stack.pop() - stack.pop()))
            "*" -> stack.push(stack.pop() * stack.pop())
            else -> {
                stack.push(it.toInt())
            }
        }
    }
    File("output.txt").bufferedWriter().use { it.write(stack.pop().toString()) }
}