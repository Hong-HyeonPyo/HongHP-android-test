package rocklike.compose

import org.junit.Test

import org.junit.Assert.*

fun main() {
    val list = List<Int>(5, { it+1 }) // 1,2,3,4,5
    val l = list.map { it * 10 }.filter { it>20 }.first()

    println(l)

//    println(list)
//    println( test(1,2,3,4,5) )
//    println( test( *list.toIntArray() ) )

}

fun test(vararg k:Int): Int {
    return k.size
}