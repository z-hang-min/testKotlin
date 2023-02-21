package com.tz.javalibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * created by zm on 2023/2/16
 *
 * @Description:
 */
class TestHashMap {
    public static void main(String[] args) {
        ArrayList<String> ss = new ArrayList<>(1);
        ss.add("1");

        LinkedList<String> list = new LinkedList<>();
        list.add("ssss");
        list.add("ssss");
        list.add("ssss");
        list.add("ssss");
        list.add(0, "hello");
        list.add(3, "hello3");
        list.remove(0);
        System.out.println("linkedlist=" + list);
        list.remove(2);
        System.out.println("linkedlist=" + list);
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("sss");
        hashSet.add("sss");
        hashSet.add("sss");
        System.out.println("hashSet=" + hashSet);
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("jjj");
        treeSet.add("jjj1");
        treeSet.add("jjj3");
        treeSet.add("jjj2");
        System.out.println("treeSet=" + treeSet);

        HashMap<String, String> hashMap = new HashMap<>(10);
        hashMap.put("1", "one");

        Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("3", 1);
        hashtable.put("5", 2);
        hashtable.put("5", 1);
        System.out.println(hashtable.toString());
        //ThreadLocal

    }
}
