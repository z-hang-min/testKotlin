// IPersonManager.aidl
package com.tz.personaidlserver;
import com.tz.personaidlserver.Person;
// Declare any non-default types here with import statements

interface IPersonManager {

    String addPerson(in Person prson);//in 只允许客户端把数据传给服务端
    String outPerson(out Person person);//out 只允许服务端把数据传给客户端
    String inOutPerson(inout Person person);//双向通信
    List<Person> getPersonList();
}