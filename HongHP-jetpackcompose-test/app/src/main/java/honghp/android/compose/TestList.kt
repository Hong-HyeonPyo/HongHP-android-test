package honghp.android.compose

import honghp.android.compose.material3.Weather

fun main() {
//    testList()
//    test2()
    testCollection()
}

fun testCollection(){
    // 리스트, Set(집합) , Map(HashMap)
//    var m:Map<String, String> = HashMap<String, String>()
    var m2 = mutableMapOf<String, String>()
    m2["a"] = "aa"
    m2["b"] = "bb"
    m2["a"]="ccc"
    println("${m2}")
    println("=================")
    var s = mutableSetOf<Int>()
    s.add(1)


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