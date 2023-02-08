package com.tz.facetest.aboutt

/**
 * created by zm on 2023/2/2

 * @Description:

 */
class SimpleData<out T>(val data:T?){

    fun get(): T? {
        return data
    }
}