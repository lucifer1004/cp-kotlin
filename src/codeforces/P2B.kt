import java.lang.Math.min
import java.util.Scanner

fun minTrace(arr: Array<IntArray>): Pair<Int, String> {
    val n = arr.size
    var cnt = Array(n) { IntArray(n) { Int.MAX_VALUE } }
    var trace = Array(n) { CharArray(n) { 'D' } }
    cnt[0][0] = 0
    for (i in 0 until n)
        for (j in 0 until n) {
            if (i > 0 && cnt[i - 1][j] < cnt[i][j]) {
                cnt[i][j] = cnt[i - 1][j]
                trace[i][j] = 'D'
            }
            if (j > 0 && cnt[i][j - 1] < cnt[i][j]) {
                cnt[i][j] = cnt[i][j - 1]
                trace[i][j] = 'R'
            }
            cnt[i][j] += arr[i][j]
        }
    var path = CharArray(n * 2 - 2) { 'D' }
    var ci = n - 1;
    var cj = n - 1;
    for (i in 0 until 2 * n - 2) {
        path[i] = trace[ci][cj];
        if (path[i] == 'D') ci--;
        else cj--;
    }
    path.reverse()
    return Pair(cnt[n - 1][n - 1], String(path))
}

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    var two = Array(n) { IntArray(n) }
    var five = Array(n) { IntArray(n) }
    var zi = -1
    var zj = -1
    for (i in 0 until n)
        for (j in 0 until n) {
            var num = scanner.nextInt()
            if (num == 0) {
                zi = i
                zj = j
                two[i][j] = 1000000000
                five[i][j] = 1000000000
                continue
            }
            while (num % 2 == 0) {
                two[i][j]++
                num /= 2
            }
            while (num % 5 == 0) {
                five[i][j]++
                num /= 5
            }
        }
    var (goTwo, twoPath) = minTrace(two)
    var (goFive, fivePath) = minTrace(five)
    var ans = min(goTwo, goFive)
    if (ans > 1 && zi >= 0) {
        println(1)
        for (i in 0 until zi) print('D')
        for (j in 0 until zj) print('R')
        for (i in zi until n - 1) print('D')
        for (j in zj until n - 1) print('R')
        return
    }
    println(ans)
    if (ans == goTwo) {
        println(twoPath)
    } else {
        println(fivePath)
    }
}