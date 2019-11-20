package io.blockio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fubin on 2019-11-17.
 */
public class ThreadPooledServer implements Runnable {
    // 服务端口
    protected int serverPort = 8080;
    // 服务端Socket
    protected ServerSocket serverSocket = null;
    // 服务停止标志
    protected boolean isStopped = false;
    // 运行的线程
    protected Thread runningThread = null;
    // 初始化线程池
    protected ExecutorService threadPool = Executors.newFixedThreadPool(10);
    // 构造函数设置端口
    public ThreadPooledServer(int port) {
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
                    System.out.println("Thread Pooled Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            try {
                // 处理客户端请求
                this.threadPool.execute(new WorkerRunnable(clientSocket,"Thread Pooled Server"));
            } catch (Exception e) {
                // log exception and go on to next request.
            }
        }
        this.threadPool.shutdown();
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
        ThreadPooledServer server = new ThreadPooledServer(9000);
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
