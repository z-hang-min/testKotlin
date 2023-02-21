package com.tz.sockertest.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Set;

/**
 * created by zm on 2023/2/13
 *
 * @Description:nio通信服务端处理器
 */
class NioServerHandle implements Runnable {
    private volatile boolean started;
    private ServerSocketChannel mServerSocketChannel;
    private Selector mSelector;

    public NioServerHandle(int port) {
        try {
            mSelector = Selector.open();
            mServerSocketChannel = ServerSocketChannel.open();
            mServerSocketChannel.configureBlocking(false);
            mServerSocketChannel.socket().bind(new InetSocketAddress(port)
            );
            //channel注册到selector
            mServerSocketChannel.register(mSelector, SelectionKey.OP_ACCEPT);

            started = true;
            System.out.println("服务器已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (started) {
            try {
                //每隔一秒被唤醒一次
                mSelector.select(1000);
                //拿到事件的集合
                Set<SelectionKey> selectionKeySet = mSelector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInputKey(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInputKey(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                System.out.println("有客户端连接");
                sc.configureBlocking(false);
                sc.register(mSelector, SelectionKey.OP_READ);
            }//读数据
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(byteBuffer);
                if (readBytes > 0) {
                    //切换模式
                    byteBuffer.flip();
                    byte[] bytes = new byte[readBytes];
                    byteBuffer.get(bytes);
                    String msg = new String(bytes, "UTF-8");
                    System.out.println("服务器收到消息：" + msg);
                    String result = "i know";
                    //发送应答消息
                    doWrite(sc, result);
                } else if (readBytes < 0) {
                    //链路已经关闭，释放资源
                    key.cancel();
                    sc.close();
                }

            }
        }
    }

    private void doWrite(SocketChannel sc, String result) throws IOException{
        byte[] bytes = result.getBytes(StandardCharsets.UTF_8);
        ByteBuffer write = ByteBuffer.allocate(bytes.length);
        write.put(bytes);
        write.flip();
        sc.write(write);
    }

    public void stop() {
        started = false;
    }
}
