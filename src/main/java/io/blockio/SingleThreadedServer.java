package io.blockio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fubin on 2019-11-17.
 *
 *
 * 单线程的服务器不是服务器的最佳设计，但是很好地说明了服务器的生命周期。
 *
 *
 */
public class SingleThreadedServer implements Runnable {

    // 服务端口
    protected int serverPort = 8080;
    // 服务端Socket
    protected ServerSocket serverSocket = null;
    // 服务停止标志
    protected boolean isStopped = false;
    // 运行的线程
    protected Thread runningThread = null;

    // 构造函数设置端口
    public SingleThreadedServer(int port) {
        this.serverPort = port;
    }

    // 线程构建服务
    public void run() {
        // 当前线程的引用保存到变量中
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        // 创建ServerSocket，并赋值
        openServerSocket();
        // 服务没有停止，默认没有停止
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                // 为连接进来的客户端连接建立Socket
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            try {
                // 处理客户端请求
                processClientRequest(clientSocket);
            } catch (Exception e) {
                // log exception and go on to next request.
            }
        }
        System.out.println("Server Stopped.");
    }

    private void processClientRequest(Socket clientSocket) throws Exception {
        // 输入流用于接收客户端数据
        InputStream input = clientSocket.getInputStream();
        // 输出流用于发送数据给客户端
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();
        // 响应报文
        byte[] responseDocument = new String("<html><body>Singlethreaded Server: " + time + "</body></html>")
                .getBytes("UTF-8");
        // 响应头报文
        byte[] responseHeader = new String("HTTP/1.1 200 OK\r\n" + "Content-Type: text/html; charset=UTF-8\r\n"
                + "Content-Length: " + responseDocument.length + "\r\n\r\n").getBytes("UTF-8");
        // 写入输出流
        output.write(responseHeader);
        output.write(responseDocument);
        output.close();
        input.close();
        System.out.println("Request processed: " + time);
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }

    public static void main(String[] args) {
        SingleThreadedServer server = new SingleThreadedServer(9000);
        new Thread(server).start();
        try {
            Thread.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
