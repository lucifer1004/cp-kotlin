package kattis

import java.util.Scanner

object SumsSquaredDigitsKt {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        val p = scanner.nextInt()
        for (i in 0 until p) {
            val k = scanner.nextInt()
            val b = scanner.nextInt()
            var n = scanner.nextInt()
            var ans = 0
            while (n > 0) {
                ans += (n % b) * (n % b);
                n /= b
            }
            println("$k $ans")
        }
    }
}
