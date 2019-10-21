import java.util.Scanner
import java.lang.Math.*

fun main() {
    class Cell(s: String) {
        val col = s[0] - 'a'
        val row = s[1].toInt()
    }

    val scanner = Scanner(System.`in`)
    val source = Cell(scanner.nextLine())
    val target = Cell(scanner.nextLine())

    var deltaCol = target.col - source.col
    var deltaRow = target.row - source.row
    val absoluteCol = abs(deltaCol)
    val absoluteRow = abs(deltaRow)
    val steps = max(absoluteCol, absoluteRow)
    println(steps)

    for (i in 0 until steps) {
        if (deltaCol > 0) {
            print('R')
            deltaCol--
        } else if (deltaCol < 0) {
            print('L')
            deltaCol++
        }

        if (deltaRow > 0) {
            print('U')
            deltaRow--
        } else if (deltaRow < 0) {
            print('D')
            deltaRow++
        }

        println()
    }
}