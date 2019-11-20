package io.blockio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fubin on 2019-11-17.
 */
public class MutiThreadedServer implements Runnable {
    // 服务端口
    protected int serverPort = 8080;
    // 服务端Socket
    protected ServerSocket serverSocket = null;
    // 服务停止标志
    protected boolean isStopped = false;
    // 运行的线程
    protected Thread runningThread = null;

    // 构造函数设置端口
    public MutiThreadedServer(int port) {
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
                    System.out.println("Muti Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            try {
                // 处理客户端请求
                new Thread(new WorkerRunnable(clientSocket,"Muti Thread Server")).start();
            } catch (Exception e) {
                // log exception and go on to next request.
            }
        }
        System.out.println("Server Stopped.");
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
        MutiThreadedServer server = new MutiThreadedServer(9000);
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
