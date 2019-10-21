package kattis

import java.util.Scanner

object TaisFormulaKt {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        val n = scanner.nextInt()
        var startTime = scanner.nextLong()
        var startGlucose = scanner.nextDouble()
        var total = 0.0
        for (i in 1 until n) {
            val newTime = scanner.nextLong()
            val newGlucose = scanner.nextDouble()
            total += (newGlucose + startGlucose) / 2 * (newTime - startTime)
            startTime = newTime
            startGlucose = newGlucose
        }
        print(total / 1000)
    }
}
