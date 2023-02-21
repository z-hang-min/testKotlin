package com.tz.sockertest.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * created by zm on 2023/2/9
 *
 * @Description: //模拟socket通信，服务端
 */
class MyServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(100001));
        while (true) {
            new Thread(new Task(serverSocket.accept()));
        }

    }
}

class Task implements Runnable {
    private Socket mSocket;

    public Task(Socket socket) {
        mSocket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(mSocket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(mSocket.getOutputStream());) {
            System.out.println("objectInputStream==" + objectInputStream.read());
            objectOutputStream.writeUTF("hello" + objectInputStream.read());
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
