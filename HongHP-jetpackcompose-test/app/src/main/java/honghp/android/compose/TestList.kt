package honghp.android.compose

import honghp.android.compose.material3.Weather

fun main() {
    testList()
//    test2()
}

fun test2(){
    var a: Weather = Weather("a", "b", 10, "20")
    println("$a")
}

fun testList(){
    val list2 = (1..10).filter { it < 100 }.toList()
    println(list2)

    val list3 = list2.map { "===$it===" }.forEach{println(it)}
    println(list3)

}