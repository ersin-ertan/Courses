package module1

fun main(args:Array<String>) {


    listOf<Int>(1, 2, 3, 6, 5, 4, 5, 55, 44, 654, 342, 44, 54, 12, 54).forEach {
        println(it)
        if (it == 44) {
            println("Found")
            return // will stop and break out of the search
//            return@forEach // will continue searching through the list
        }
    }
}