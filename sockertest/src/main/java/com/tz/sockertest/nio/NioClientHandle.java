package com.tz.sockertest.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * created by zm on 2023/2/13
 *
 * @Description:
 */
class NioClientHandle implements Runnable {
    private String host;
    private int port;
    private volatile boolean started;
    private Selector mSelector;
    private SocketChannel mSocketChannel;

    public NioClientHandle(String ip, int port) {
        this.host = ip;
        this.port = port;
        try {
            mSelector = Selector.open();
            mSocketChannel = SocketChannel.open();
            mSocketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (started) {
            try {
                mSelector.select(1000);
                //拿到事件的集合
                Set<SelectionKey> selectionKeySet = mSelector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
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

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
//连接上了
            if (key.isConnectable()) {
                //连接完成
                if (sc.finishConnect()) {
                    mSocketChannel.register(mSelector, SelectionKey.OP_READ);

                }

            }//读数据
            if (key.isReadable()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(byteBuffer);
                if (readBytes > 0) {
                    //切换模式
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String msg = new String(bytes, "UTF-8");
                    System.out.println("客户端收到：" + msg);
                    String result = "i know";
                } else if (readBytes < 0) {
                    //链路已经关闭，释放资源
                    key.cancel();
                    sc.close();
                }

            }
        }
    }

    private void doWrite(SocketChannel sc, String result) throws IOException {
        byte[] bytes = result.getBytes(StandardCharsets.UTF_8);
        ByteBuffer write = ByteBuffer.allocate(bytes.length);
        write.put(bytes);
        write.flip();
        sc.write(write);
    }

    private void doConnect() throws IOException {
        if (mSocketChannel.connect(new InetSocketAddress(host, port))) {
            //连接完成
            mSocketChannel.register(mSelector, SelectionKey.OP_READ);
        } else {
            //连接未完成，还在三次握手
            mSocketChannel.register(mSelector, SelectionKey.OP_CONNECT);

        }
    }

    public void sendMsg(String msg) throws Exception{
        doWrite(mSocketChannel,msg);
    }
}
