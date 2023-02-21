package com.tz.sockertest.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * created by zm on 2023/2/9
 *
 * @Description:
 */
class MyClient {
    public static void main(String[] args) throws Exception {
        Socket mSocket = new Socket();
        mSocket.connect(new InetSocketAddress("127.0.0.1", 100001));

        try (ObjectInputStream objectInputStream = new ObjectInputStream(mSocket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(mSocket.getOutputStream());) {
            objectOutputStream.writeUTF("leo");
            objectOutputStream.flush();
            System.out.println("client objectInputStream==" + objectInputStream.read());


        } catch (IOException e) {
            e.printStackTrace();
            mSocket.close();

        }
    }
}
