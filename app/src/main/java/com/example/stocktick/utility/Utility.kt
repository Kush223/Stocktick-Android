package com.example.stocktick.utility

object Utility {
    /**
     * This loop will iterate n times
     * i will go from 1 to n inclusive*/
    fun loop(n : Int, body : (i : Int)->Unit){
        for (a in 1 .. n){
            body(a)
        }
    }
}