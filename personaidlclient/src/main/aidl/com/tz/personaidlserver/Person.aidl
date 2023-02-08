// Person.aidl
package com.tz.personaidlserver;
// Declare any non-default types here with import statements
parcelable Person;
//声明Person对象被序列化了，否者也不能被AIDL识别
//用来定义parcelable对象，以供其他AIDL文件使用AIDL中非默认支持的数据类型，

