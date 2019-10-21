import java.util.*

fun main() {
    class Vehicle(val volume: Int, val capacity: Int, val id: Int) : Comparable<Vehicle> {
        override fun compareTo(other: Vehicle): Int = other.capacity * this.volume - this.capacity * other.volume
    }

    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val v = scanner.nextInt()
    val vehicles = arrayListOf<Vehicle>()
    for (i in 0 until n) {
        val volume = scanner.nextInt()
        val capacity = scanner.nextInt()
        vehicles.add(Vehicle(volume, capacity, i + 1))
    }
    vehicles.sort()
    var ans = 0
    var remaining = v
    val kayaks = Stack<Int>()
    val catamarans = Stack<Int>()
    var firstUnchosenCatamaran = -1
    for (i in 0 until n) {
        if (vehicles[i].volume <= remaining) {
            remaining -= vehicles[i].volume
            ans += vehicles[i].capacity
            if (vehicles[i].volume == 1) {
                kayaks.push(i)
            } else {
                catamarans.push(i)
            }
        } else if (vehicles[i].volume == 2 && firstUnchosenCatamaran < 0) {
            firstUnchosenCatamaran = i
        }
    }

    if (firstUnchosenCatamaran >= 0) {
        if (remaining == 1 && kayaks.size >= 1) {
            val lastKayak = kayaks.peek()
            if (vehicles[firstUnchosenCatamaran].capacity > vehicles[lastKayak].capacity) {
                kayaks.pop()
                catamarans.push(firstUnchosenCatamaran)
                ans += vehicles[firstUnchosenCatamaran].capacity - vehicles[lastKayak].capacity
            }
        }

        if (remaining == 0 && kayaks.size >= 2) {
            val lastKayak = kayaks.pop()
            val secondLastKayak = kayaks.peek()
            val kayakCapacity = vehicles[lastKayak].capacity + vehicles[secondLastKayak].capacity
            if (vehicles[firstUnchosenCatamaran].capacity > kayakCapacity) {
                kayaks.pop()
                catamarans.push(firstUnchosenCatamaran)
                ans += vehicles[firstUnchosenCatamaran].capacity - kayakCapacity
            } else {
                kayaks.push(lastKayak)
            }
        }
    }


    println(ans)
    while (!kayaks.empty()) {
        println(vehicles[kayaks.pop()].id)
    }

    while (!catamarans.empty()) {
        println(vehicles[catamarans.pop()].id)
    }
}