package com.tz.facetest.aboutthread

import kotlinx.coroutines.delay
import kotlin.concurrent.thread

/**
 * created by zm on 2023/2/4

 * @Description:

 */
class volitadTest {

}
@Volatile
var count = 0
@Volatile
var flag: Boolean = false
fun main() {
    thread {
        println("thread1-start")
        while (!flag) {
                count++
        }
        println("thread1-end  $count")
    }
    Thread.sleep(1000L)
    thread {
        println("thread2-start")
       flag=true
        println("thread2-end $count")
    }
}