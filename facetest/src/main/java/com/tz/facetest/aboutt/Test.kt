package com.tz.facetest.aboutt

/**
 * created by zm on 2023/2/2

 * @Description:

 */
//泛型实化test
fun main() {
    val result = getGenericType<Int>()
    val result2 = getGenericType<String>()
    print("result is $result \n")
    print("result2 is $result2 ")

    val student: Student = Student("zhangsan", 19)
    val data=SimpleData<Student>(student)
    handleSimpleData(data)
    val  studentdata=data.get()

}

fun handleSimpleData(ssda: SimpleData<Person>) {
    val person = ssda.get()

}

inline fun <reified T> getGenericType() = T::class.java