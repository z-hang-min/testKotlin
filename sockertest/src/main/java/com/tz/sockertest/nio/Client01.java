package com.tz.sockertest.nio;

/**
 * created by zm on 2023/2/13
 *
 * @Description:
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//TCP协议Socket：客户端
public class Client01 {
    private static NioClientHandle sNioClientHandle;

    public static void start() {
        sNioClientHandle = new NioClientHandle("127.0.0.1", 8888);
        new Thread(sNioClientHandle, "client").start();
    }

    public static boolean sendMsg(String msg) throws Exception{
        sNioClientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) throws Exception {
        start();
        Scanner s = new Scanner(System.in);

        while (sendMsg(s.next())) ;
    }
}

