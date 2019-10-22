package kattis

import java.util.*

object DungeonKt {
    class Point(val level: Int, val row: Int, val col: Int) {
        override fun equals(other: Any?): Boolean {
            return if (other is Point)
                this.level == other.level && this.row == other.row && this.col == other.col
            else
                false
        }

        override fun toString(): String {
            return "Level: ${this.level} Row: ${this.row} Col: ${this.col}"
        }
    }

    private val dl = arrayOf(-1, 0, 0, 0, 0, 1)
    private val dr = arrayOf(0, 0, -1, 0, 1, 0)
    private val dc = arrayOf(0, -1, 0, 1, 0, 0)

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        while (true) {
            val l = scanner.nextInt()
            val r = scanner.nextInt()
            val c = scanner.nextInt()
            if (l == 0) break

            scanner.nextLine()
            val dungeon = Array(l) { arrayListOf<String>() }
            var start = Point(0, 0, 0)
            var end = Point(0, 0, 0)

            for (i in 0 until l) {
                for (j in 0 until r) {
                    dungeon[i].add(scanner.nextLine())
                    for (k in 0 until c) {
                        if (dungeon[i][j][k] == 'S') {
                            start = Point(i, j, k)
                        }
                        if (dungeon[i][j][k] == 'E') {
                            end = Point(i, j, k)
                        }
                    }
                }
                scanner.nextLine()
            }

            val bfs: Queue<Pair<Point, Int>> = LinkedList()
            val visited = Array(l) { Array(r) { BooleanArray(c) { false } } }
            bfs.add(Pair(start, 0))
            visited[start.level][start.row][start.col] = true
            var escaped = false
            while (!bfs.isEmpty()) {
                val head = bfs.poll()
                if (head.first == end) {
                    println("Escaped in ${head.second} minute(s).")
                    escaped = true
                    break
                }
                for (k in 0 until 6) {
                    val nl = head.first.level + dl[k]
                    val nr = head.first.row + dr[k]
                    val nc = head.first.col + dc[k]
                    if (nl < 0 || nl >= l || nr < 0 || nr >= r || nc < 0 || nc >= c) continue
                    if (dungeon[nl][nr][nc] == '#' || visited[nl][nr][nc]) continue
                    visited[nl][nr][nc] = true
                    bfs.add(Pair(Point(nl, nr, nc), head.second + 1))
                }
            }
            if (!escaped) println("Trapped!")
        }
    }
}
