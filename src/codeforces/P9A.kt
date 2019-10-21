import java.lang.Math.max
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var highestPoints = scanner.nextInt()
    highestPoints = max(highestPoints, scanner.nextInt())
    println(when (highestPoints) {
        1 -> "1/1"
        3 -> "2/3"
        4 -> "1/2"
        5 -> "1/3"
        else -> (7 - highestPoints).toString() + "/6"
    })
}